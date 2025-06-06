/*
 * Copyright (c) 2020 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.web.admin;

import com.elm.shj.admin.portal.services.dto.*;
import com.elm.shj.admin.portal.web.AbstractControllerTestSuite;
import com.elm.shj.admin.portal.web.navigation.Navigation;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;

import java.util.*;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for controller {@link UserManagementController}
 *
 * @author Aymen DHAOUI
 * @since 1.0.0
 */
public class UserManagementControllerTest extends AbstractControllerTestSuite {

    private static final long TEST_USER_ID = 5;

    private List<UserDto> users;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() throws Exception {
        initUserList();
        when(userService.save(Mockito.any(UserDto.class))).then((Answer<UserDto>) this::mockSaveUser);
        when(userService.findAllNotDeleted(any(Pageable.class), anyLong())).thenReturn(new PageImpl<>(users));
        mockSuccessfulLogin();
        triggerLogin();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tearDown() {
        // nothing to do
    }

    @Test
    public void test_list_all() throws Exception {
        String url = Navigation.API_USERS + "/list";
        mockMvc.perform(get(url).cookie(tokenCookie).with(csrf())).andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.totalElements", is(users.size())))
                .andExpect(jsonPath("$.content", hasSize(users.size())))
                .andExpect(jsonPath("$.content[0].id", is((int) users.get(0).getId())));


        verify(userService, times(1)).findAllNotDeleted(any(Pageable.class), anyLong());

    }

    @Test
    public void test_find_user_success() throws Exception {
        int userIdToView = random.nextInt(9) + 1;
        String url = Navigation.API_USERS + "/find/" + userIdToView;
        mockMvc.perform(get(url).cookie(tokenCookie).with(csrf())).andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(userIdToView)));

        verify(userService, times(1)).findOne((long) userIdToView);
    }

    @Test
    public void test_find_user_does_not_exist() throws Exception {
        int userIdDoesNotExist = 2222;
        String url = Navigation.API_USERS + "/find/" + userIdDoesNotExist;
        mockMvc.perform(get(url).cookie(tokenCookie).with(csrf())).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$").doesNotExist());

        verify(userService, times(1)).findOne((long) userIdDoesNotExist);
    }

    @Test
    public void test_update_user_success() throws Exception {
        String url = Navigation.API_USERS + "/update";
        UserDto user = buildParams(true);
        final long userIdToUpdate = user.getId();

        mockMvc.perform(post(url).cookie(tokenCookie).contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectToJson(user)).with(csrf()))
                .andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$.id", is((int) userIdToUpdate)));

    }

    @Test
    public void test_update_user_validation_error() throws Exception {
        String url = Navigation.API_USERS + "/update";
        UserDto user = buildParams(true);

        user.setFamilyName("");
        user.setEmail("invalid_email");

        mockMvc.perform(post(url).cookie(tokenCookie).contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectToJson(user)).with(csrf())).andExpect(status().is(400)).andDo(print());
    }

    @Test
    public void test_delete_user_success() throws Exception {
        int userIdToDelete = random.nextInt(9) + 1;
        String url = Navigation.API_USERS + "/delete/" + userIdToDelete;
        int initialUsersCount = users.size();

        mockMvc.perform(post(url).cookie(tokenCookie).with(csrf())).andDo(print()).andExpect(jsonPath("$").doesNotExist());

        verify(userService, times(1)).deleteUser(userIdToDelete);

        assertEquals(users.size(), initialUsersCount - 1);
    }

    @Test
    public void test_change_user_password_invalid() throws Exception {
        String newPassword = "invalidPassword";
        String url = Navigation.API_USERS + "/change-password";

        ChangePasswordCmd params = new ChangePasswordCmd();
        params.setOldPassword(TEST_USER_PASSWORD);
        params.setNewPassword(newPassword);
        params.setNewPasswordConfirm(newPassword);

        mockMvc.perform(post(url).cookie(tokenCookie).content(objectToJson(params)).contentType(MediaType.APPLICATION_JSON_UTF8).with(csrf())).andDo(print())
                .andExpect(status().isBadRequest()).andExpect(jsonPath("$.errors").exists());
    }

    @Test
    public void test_change_user_password_empty() throws Exception {
        String newPassword = "";
        String url = Navigation.API_USERS + "/change-password";

        ChangePasswordCmd params = new ChangePasswordCmd();
        params.setOldPassword(TEST_USER_PASSWORD);
        params.setNewPassword(newPassword);
        params.setNewPasswordConfirm(newPassword);

        mockMvc.perform(post(url).cookie(tokenCookie).content(objectToJson(params)).contentType(MediaType.APPLICATION_JSON_UTF8).with(csrf())).andDo(print())
                .andExpect(status().isBadRequest()).andExpect(jsonPath("$.errors").exists());
    }

    @Test
    public void test_change_user_password_does_not_match() throws Exception {
        String oldPasswordDoesNotMatch = TEST_USER_PASSWORD + "Delta";
        String newPassword = "newC0mpl@x";
        String url = Navigation.API_USERS + "/change-password";

        ChangePasswordCmd params = new ChangePasswordCmd();
        params.setOldPassword(oldPasswordDoesNotMatch);
        params.setNewPassword(newPassword);
        params.setNewPasswordConfirm(newPassword);

        mockMvc.perform(post(url).cookie(tokenCookie).content(objectToJson(params)).contentType(MediaType.APPLICATION_JSON_UTF8).with(csrf())).andDo(print())
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void test_change_user_password_contains_username() throws Exception {
        String newPassword = "P@ssw0rd";// + TEST_USER_NAME;
        String url = Navigation.API_USERS + "/change-password";

        ChangePasswordCmd params = new ChangePasswordCmd();
        params.setOldPassword(TEST_USER_PASSWORD);
        params.setNewPassword(newPassword);
        params.setNewPasswordConfirm(newPassword);

        mockMvc.perform(post(url).cookie(tokenCookie).content(objectToJson(params)).contentType(MediaType.APPLICATION_JSON_UTF8).with(csrf())).andDo(print())
                .andExpect(status().isBadRequest()).andExpect(jsonPath("$.errors").exists());
    }

    @Test
    public void test_change_user_password_success() throws Exception {
        String newPassword = "newC0mpl@x";
        String url = Navigation.API_USERS + "/change-password";

        ChangePasswordCmd params = new ChangePasswordCmd();
        params.setOldPassword(TEST_USER_PASSWORD);
        params.setNewPassword(newPassword);
        params.setNewPasswordConfirm(newPassword);

        String encodedOldPassword = passwordEncoder.encode(TEST_USER_PASSWORD);
        when(userService.retrievePasswordHash(Long.parseLong(TEST_USER_NIN))).thenReturn(encodedOldPassword);

        String code = userService.retrievePasswordHash(Long.parseLong(TEST_USER_NIN));

        assertEquals(code, encodedOldPassword);

        mockMvc.perform(post(url).cookie(tokenCookie).content(objectToJson(params)).contentType(MediaType.APPLICATION_JSON_UTF8).with(csrf())).andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$").doesNotExist());

        verify(userService, times(1)).updateUserPassword(eq(Long.parseLong(TEST_USER_NIN)), anyString());
    }

    @Test
    void test_activate_user()  throws Exception {
        String url = Navigation.API_USERS + "/activate/"+TEST_USER_ID;
        mockMvc.perform(post(url).cookie(tokenCookie).with(csrf())).andDo(print()).andExpect(status().isOk());
        verify(userService, times(1)).activateUser(TEST_USER_ID);
    }

    @Test
    void test_deactivate_user()  throws Exception {
        String url = Navigation.API_USERS + "/deactivate/"+TEST_USER_ID;
        mockMvc.perform(post(url).cookie(tokenCookie).with(csrf())).andDo(print()).andExpect(status().isOk());
        verify(userService, times(1)).deactivateUser(TEST_USER_ID);
    }




    /**
     * Utility method to initiate users list
     */
    private void initUserList() {
        users = new ArrayList<>();
        UserDto user;
        RoleDto role = new RoleDto();
        RoleAuthorityDto roleAuthority = new RoleAuthorityDto();
        AuthorityLookupDto authority = new AuthorityLookupDto();
        roleAuthority.setAuthority(authority);
        authority.setCode(AuthorityConstants.ADD_USER);
        role.setRoleAuthorities(new HashSet<>(Collections.singletonList(roleAuthority)));
        UserRoleDto userRole = new UserRoleDto();
        userRole.setUser(loggedInUser);
        userRole.setRole(role);
        userRole.setMainRole(true);
        for (long i = 0; i < 10; i++) {
            Long nin = new Long("123456789" + i);
            user = new UserDto();
            user.setId(i + 1);
            user.setNin(nin);
            user.setUserRoles(Collections.singleton(userRole));
            user.setPassword(TEST_USER_PASSWORD);
            user.setPasswordHash(passwordEncoder.encode(TEST_USER_PASSWORD));
            //user.setAuthorities(Arrays.asList(authority));
            users.add(user);
            when(userService.findOne(i + 1)).thenReturn(user);
            when(userService.save(user)).thenReturn(user);
            when(userService.findByNin(nin)).thenReturn(Optional.of(user));

            doAnswer((Answer<Void>) invocation -> {
                Object[] args = invocation.getArguments();
                users.remove(users.get(Integer.parseInt("" + args[0]) - 1));
                return null;
            }).when(userService).deleteUser(i + 1);
        }
        when(userService.findAll()).thenReturn(users);
    }

    /**
     * Used to mock user creation
     *
     * @param invocation the mock context
     * @return the mocked user after creation
     */
    private UserDto mockSaveUser(InvocationOnMock invocation) {
        UserDto userToChange = invocation.getArgument(0);
        if (userToChange == null) {
            return null;
        }
        if (userToChange.getId() > 0) {
            users.set((int) userToChange.getId(), userToChange);
        } else {
            userToChange.setId(NEW_USER_ID);
            users.add(userToChange);
        }
        return userToChange;
    }

    /**
     * Utility method to initiate users list
     */
    private UserDto buildParams(boolean isUpdate) {

        UserDto user = new UserDto();
        int userIdToChange = 0;
        if (isUpdate) {
            userIdToChange = random.nextInt(9) + 1;
        }
        user.setId(userIdToChange);
        user.setDateOfBirthHijri(14101010);
        user.setDateOfBirthGregorian(DateUtils.addDays(new Date(), -20 * 365));
        user.setEmail("email@company.com");
        user.setFamilyName("familyName");
        user.setFirstName("firstName");
        user.setGrandFatherName("grandFatherName");
        user.setFatherName("fatherName");
        user.setMobileNumber(512345678);
        user.setNin(1234567897L);
        user.setPassword(TEST_USER_PASSWORD);

        return user;
    }

}
