package com.elm.shj.admin.portal.orm.entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
/**
 * The persistent class for the shc_supplication_Lk database table.
 *
 * @author Nihed Sidhom
 * @since 1.1.0
 */
@Entity
@Table(name = "shc_supplication_type_lk")
@NamedQuery(name = "JpaSupplicationTypeLookup.findAll", query = "SELECT j FROM JpaSupplicationTypeLookup j")
@Getter
@Setter
@NoArgsConstructor
public class JpaSupplicationTypeLookup  extends JpaLocalizedLookup{
    private static final long serialVersionUID = -4534440463041537927L;
}