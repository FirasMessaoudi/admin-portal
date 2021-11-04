import {CardStatus} from "@model/enum/card-status.enum";


export var cardStatusActions = new Map<string, string[]>();
cardStatusActions.set(CardStatus.ACTIVE, ['SUSPEND_CARD', 'CANCEL_CARD']);
cardStatusActions.set(CardStatus.CANCELLED, ['REISSUE_CARD']);
cardStatusActions.set(CardStatus.SUSPENDED, ['ACTIVATE_CARD', 'CANCEL_CARD']);
cardStatusActions.set(CardStatus.READY_TO_PRINT, ['CANCEL_CARD']);
cardStatusActions.set(CardStatus.SENT_FOR_PRINT, ['CANCEL_CARD']);
cardStatusActions.set(CardStatus.PRINTED, ['CANCEL_CARD', 'ACTIVATE_CARD']);
cardStatusActions.set(CardStatus.DISTRIBUTED, ['CANCEL_CARD', 'ACTIVATE_CARD']);
cardStatusActions.set(CardStatus.WAITING_TO_SEND, ['CANCEL_CARD']);


export enum CardAllowedActions {
  ACTIVATE_CARD = 'ACTIVATE_CARD',
  SUSPEND_CARD = 'SUSPEND_CARD',
  CANCEL_CARD = 'CANCEL_CARD',
  REISSUE_CARD = 'REISSUE_CARD',
}



