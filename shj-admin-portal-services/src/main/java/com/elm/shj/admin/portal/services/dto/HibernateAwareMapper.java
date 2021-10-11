package com.elm.shj.admin.portal.services.dto;

import org.hibernate.collection.spi.PersistentCollection;
import org.mapstruct.BeforeMapping;
import org.mapstruct.TargetType;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;


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