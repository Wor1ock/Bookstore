DELETE FROM SYS_SCHEDULED_TASK
WHERE ID = '03cbea49-ab37-9df1-d700-a1b1e13896cf';

insert into SYS_SCHEDULED_TASK
(ID, CREATE_TS, CREATED_BY, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, DEFINED_BY, SYS_TENANT_ID, BEAN_NAME, METHOD_NAME, CLASS_NAME, SCRIPT_NAME, USER_NAME, IS_SINGLETON, IS_ACTIVE, PERIOD_, TIMEOUT, START_DATE, CRON, SCHEDULING_TYPE, TIME_FRAME, START_DELAY, PERMITTED_SERVERS, LOG_START, LOG_FINISH, LAST_START_TIME, LAST_START_SERVER, METHOD_PARAMS, DESCRIPTION)
values ('1dfb0077-3715-06c4-e28d-a57b80be627c', '2024-09-10 20:50:04', 'admin', '2024-09-10 20:55:09', 'admin', null, null, 'B', null, 'bookstore_OrderCheckerBean', 'checkOrders', null, null, null, null, true, 60, null, null, null, 'P', null, null, null, null, null, null, null, '<?xml version="1.0" encoding="UTF-8"?>
<params/>
', null);
