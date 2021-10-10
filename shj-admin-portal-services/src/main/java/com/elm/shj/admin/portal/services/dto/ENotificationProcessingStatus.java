package com.elm.shj.admin.portal.services.dto;

/**
 * Enum for Notification Processing Status
 *
 * @author Ahmed Ali
 * @since 1.1.0
 */
public enum ENotificationProcessingStatus {
   NEW(1);

   private final int id;

   ENotificationProcessingStatus(int id) {
      this.id = id;
   }

   public int getId() {
      return this.id;
   }
}
