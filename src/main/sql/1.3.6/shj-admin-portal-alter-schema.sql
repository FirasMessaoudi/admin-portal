USE shc_portal
GO

if not exists(select * from sys.tables where name = 'shc_chatbot_item')
create table shc_portal.shc_chatbot_item
(
    id            int           NOT NULL PRIMARY KEY IDENTITY (1, 1),
    code          varchar(40)   NOT NULL,
    lang          varchar(45)   NOT NULL,
    label         nvarchar(200) NOT NULL,
    parent_code   varchar(40)   NULL,
    creation_date smalldatetime NOT NULL default current_timestamp,
    CONSTRAINT chatbot_item_unique unique (code ASC, lang ASC)
);
GO

