/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

import org.hibernate.collection.spi.PersistentCollection;
import org.mapstruct.BeforeMapping;
import org.mapstruct.TargetType;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * Custom mapper to fetch the associated entities on demand (lazy loading).
 *
 * @author ahmed elsayed
 * @since 1.1.0
 */
public interface HibernateAwareMapper {

    @BeforeMapping
    default <T> List<T> fixLazyLoadingList(Collection<?> c, @TargetType Class<?> targetType) {

        if (!Util.wasInitialized(c)) {
            return Collections.emptyList();
        }
        return null;
    }

    @BeforeMapping
    default <T> Set<T> fixLazyLoadingSet(Collection<?> c, @TargetType Class<?> targetType) {
        if (!Util.wasInitialized(c)) {
            return Collections.emptySet();
        }
        return null;
    }

    class Util {
        static boolean wasInitialized(Object c) {
            if (!(c instanceof PersistentCollection)) {
                return true;
            }

            PersistentCollection pc = (PersistentCollection) c;
            return pc.wasInitialized();
        }
    }
}