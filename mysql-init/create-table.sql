create database if not exists checkin_database;

use checkin_database;

create table if not exists holiday_info
(
    id          bigint auto_increment
        primary key,
    date        varchar(10)                        not null comment '节假日日期',
    name        varchar(50)                        not null comment '节假日名称',
    wage        int                                null comment '薪资倍数',
    type        int                                null comment '节假日类型：0工作日、1周末、2节日、3调休',
    week        int                                null comment '周几：1-7表示周一到周日',
    create_time datetime default CURRENT_TIMESTAMP null,
    constraint uk_date
        unique (date)
)
    comment '节假日信息表';

# INSERT INTO holiday_info (id, date, name, wage, type, week, create_time) VALUES (1, '2024-01-01', '元旦', null, null, null, '2024-10-30 12:52:20');
# INSERT INTO holiday_info (id, date, name, wage, type, week, create_time) VALUES (2, '2024-01-06', '周六', null, null, null, '2024-10-30 12:52:20');
# INSERT INTO holiday_info (id, date, name, wage, type, week, create_time) VALUES (3, '2024-01-07', '周日', null, null, null, '2024-10-30 12:52:20');
# INSERT INTO holiday_info (id, date, name, wage, type, week, create_time) VALUES (4, '2024-01-13', '周六', null, null, null, '2024-10-30 12:52:20');
# INSERT INTO holiday_info (id, date, name, wage, type, week, create_time) VALUES (5, '2024-01-14', '周日', null, null, null, '2024-10-30 12:52:20');
# INSERT INTO holiday_info (id, date, name, wage, type, week, create_time) VALUES (6, '2024-01-20', '周六', null, null, null, '2024-10-30 12:52:20');
# INSERT INTO holiday_info (id, date, name, wage, type, week, create_time) VALUES (7, '2024-01-21', '周日', null, null, null, '2024-10-30 12:52:20');
# INSERT INTO holiday_info (id, date, name, wage, type, week, create_time) VALUES (8, '2024-01-27', '周六', null, null, null, '2024-10-30 12:52:20');
# INSERT INTO holiday_info (id, date, name, wage, type, week, create_time) VALUES (9, '2024-01-28', '周日', null, null, null, '2024-10-30 12:52:20');
# INSERT INTO holiday_info (id, date, name, wage, type, week, create_time) VALUES (10, '2024-02-03', '周六', null, null, null, '2024-10-30 12:52:20');
# INSERT INTO holiday_info (id, date, name, wage, type, week, create_time) VALUES (11, '2024-02-10', '初一', null, null, null, '2024-10-30 12:52:20');
# INSERT INTO holiday_info (id, date, name, wage, type, week, create_time) VALUES (12, '2024-02-11', '初二', null, null, null, '2024-10-30 12:52:20');
# INSERT INTO holiday_info (id, date, name, wage, type, week, create_time) VALUES (13, '2024-02-12', '初三', null, null, null, '2024-10-30 12:52:20');
# INSERT INTO holiday_info (id, date, name, wage, type, week, create_time) VALUES (14, '2024-02-13', '初四', null, null, null, '2024-10-30 12:52:20');
# INSERT INTO holiday_info (id, date, name, wage, type, week, create_time) VALUES (15, '2024-02-14', '初五', null, null, null, '2024-10-30 12:52:20');
# INSERT INTO holiday_info (id, date, name, wage, type, week, create_time) VALUES (16, '2024-02-15', '初六', null, null, null, '2024-10-30 12:52:20');
# INSERT INTO holiday_info (id, date, name, wage, type, week, create_time) VALUES (17, '2024-02-16', '初七', null, null, null, '2024-10-30 12:52:20');
# INSERT INTO holiday_info (id, date, name, wage, type, week, create_time) VALUES (18, '2024-02-17', '初八', null, null, null, '2024-10-30 12:52:20');
# INSERT INTO holiday_info (id, date, name, wage, type, week, create_time) VALUES (19, '2024-02-24', '周六', null, null, null, '2024-10-30 12:52:20');
# INSERT INTO holiday_info (id, date, name, wage, type, week, create_time) VALUES (20, '2024-02-25', '周日', null, null, null, '2024-10-30 12:52:20');
# INSERT INTO holiday_info (id, date, name, wage, type, week, create_time) VALUES (21, '2024-03-02', '周六', null, null, null, '2024-10-30 12:52:20');
# INSERT INTO holiday_info (id, date, name, wage, type, week, create_time) VALUES (22, '2024-03-03', '周日', null, null, null, '2024-10-30 12:52:20');
# INSERT INTO holiday_info (id, date, name, wage, type, week, create_time) VALUES (23, '2024-03-09', '周六', null, null, null, '2024-10-30 12:52:20');
# INSERT INTO holiday_info (id, date, name, wage, type, week, create_time) VALUES (24, '2024-03-10', '周日', null, null, null, '2024-10-30 12:52:20');
# INSERT INTO holiday_info (id, date, name, wage, type, week, create_time) VALUES (25, '2024-03-16', '周六', null, null, null, '2024-10-30 12:52:20');
# INSERT INTO holiday_info (id, date, name, wage, type, week, create_time) VALUES (26, '2024-03-17', '周日', null, null, null, '2024-10-30 12:52:20');
# INSERT INTO holiday_info (id, date, name, wage, type, week, create_time) VALUES (27, '2024-03-23', '周六', null, null, null, '2024-10-30 12:52:20');
# INSERT INTO holiday_info (id, date, name, wage, type, week, create_time) VALUES (28, '2024-03-24', '周日', null, null, null, '2024-10-30 12:52:20');
# INSERT INTO holiday_info (id, date, name, wage, type, week, create_time) VALUES (29, '2024-03-30', '周六', null, null, null, '2024-10-30 12:52:20');
# INSERT INTO holiday_info (id, date, name, wage, type, week, create_time) VALUES (30, '2024-03-31', '周日', null, null, null, '2024-10-30 12:52:20');
# INSERT INTO holiday_info (id, date, name, wage, type, week, create_time) VALUES (31, '2024-04-04', '清明节', null, null, null, '2024-10-30 12:52:20');
# INSERT INTO holiday_info (id, date, name, wage, type, week, create_time) VALUES (32, '2024-04-05', '清明节', null, null, null, '2024-10-30 12:52:20');
# INSERT INTO holiday_info (id, date, name, wage, type, week, create_time) VALUES (33, '2024-04-06', '清明节', null, null, null, '2024-10-30 12:52:20');
# INSERT INTO holiday_info (id, date, name, wage, type, week, create_time) VALUES (34, '2024-04-13', '周六', null, null, null, '2024-10-30 12:52:20');
# INSERT INTO holiday_info (id, date, name, wage, type, week, create_time) VALUES (35, '2024-04-14', '周日', null, null, null, '2024-10-30 12:52:20');
# INSERT INTO holiday_info (id, date, name, wage, type, week, create_time) VALUES (36, '2024-04-20', '周六', null, null, null, '2024-10-30 12:52:20');
# INSERT INTO holiday_info (id, date, name, wage, type, week, create_time) VALUES (37, '2024-04-21', '周日', null, null, null, '2024-10-30 12:52:20');
# INSERT INTO holiday_info (id, date, name, wage, type, week, create_time) VALUES (38, '2024-04-27', '周六', null, null, null, '2024-10-30 12:52:20');
# INSERT INTO holiday_info (id, date, name, wage, type, week, create_time) VALUES (39, '2024-05-01', '劳动节', null, null, null, '2024-10-30 12:52:20');
# INSERT INTO holiday_info (id, date, name, wage, type, week, create_time) VALUES (40, '2024-05-02', '劳动节', null, null, null, '2024-10-30 12:52:20');
# INSERT INTO holiday_info (id, date, name, wage, type, week, create_time) VALUES (41, '2024-05-03', '劳动节', null, null, null, '2024-10-30 12:52:20');
# INSERT INTO holiday_info (id, date, name, wage, type, week, create_time) VALUES (42, '2024-05-04', '劳动节', null, null, null, '2024-10-30 12:52:20');
# INSERT INTO holiday_info (id, date, name, wage, type, week, create_time) VALUES (43, '2024-05-05', '劳动节', null, null, null, '2024-10-30 12:52:20');
# INSERT INTO holiday_info (id, date, name, wage, type, week, create_time) VALUES (44, '2024-05-12', '周日', null, null, null, '2024-10-30 12:52:20');
# INSERT INTO holiday_info (id, date, name, wage, type, week, create_time) VALUES (45, '2024-05-18', '周六', null, null, null, '2024-10-30 12:52:20');
# INSERT INTO holiday_info (id, date, name, wage, type, week, create_time) VALUES (46, '2024-05-19', '周日', null, null, null, '2024-10-30 12:52:20');
# INSERT INTO holiday_info (id, date, name, wage, type, week, create_time) VALUES (47, '2024-05-25', '周六', null, null, null, '2024-10-30 12:52:20');
# INSERT INTO holiday_info (id, date, name, wage, type, week, create_time) VALUES (48, '2024-05-26', '周日', null, null, null, '2024-10-30 12:52:20');
# INSERT INTO holiday_info (id, date, name, wage, type, week, create_time) VALUES (49, '2024-06-01', '周六', null, null, null, '2024-10-30 12:52:20');
# INSERT INTO holiday_info (id, date, name, wage, type, week, create_time) VALUES (50, '2024-06-02', '周日', null, null, null, '2024-10-30 12:52:20');
# INSERT INTO holiday_info (id, date, name, wage, type, week, create_time) VALUES (51, '2024-06-08', '端午节', null, null, null, '2024-10-30 12:52:20');
# INSERT INTO holiday_info (id, date, name, wage, type, week, create_time) VALUES (52, '2024-06-09', '端午节', null, null, null, '2024-10-30 12:52:20');
# INSERT INTO holiday_info (id, date, name, wage, type, week, create_time) VALUES (53, '2024-06-10', '端午节', null, null, null, '2024-10-30 12:52:20');
# INSERT INTO holiday_info (id, date, name, wage, type, week, create_time) VALUES (54, '2024-06-15', '周六', null, null, null, '2024-10-30 12:52:20');
# INSERT INTO holiday_info (id, date, name, wage, type, week, create_time) VALUES (55, '2024-06-16', '周日', null, null, null, '2024-10-30 12:52:20');
# INSERT INTO holiday_info (id, date, name, wage, type, week, create_time) VALUES (56, '2024-06-22', '周六', null, null, null, '2024-10-30 12:52:20');
# INSERT INTO holiday_info (id, date, name, wage, type, week, create_time) VALUES (57, '2024-06-23', '周日', null, null, null, '2024-10-30 12:52:20');
# INSERT INTO holiday_info (id, date, name, wage, type, week, create_time) VALUES (58, '2024-06-29', '周六', null, null, null, '2024-10-30 12:52:20');
# INSERT INTO holiday_info (id, date, name, wage, type, week, create_time) VALUES (59, '2024-06-30', '周日', null, null, null, '2024-10-30 12:52:20');
# INSERT INTO holiday_info (id, date, name, wage, type, week, create_time) VALUES (60, '2024-07-06', '周六', null, null, null, '2024-10-30 12:52:20');
# INSERT INTO holiday_info (id, date, name, wage, type, week, create_time) VALUES (61, '2024-07-07', '周日', null, null, null, '2024-10-30 12:52:20');
# INSERT INTO holiday_info (id, date, name, wage, type, week, create_time) VALUES (62, '2024-07-13', '周六', null, null, null, '2024-10-30 12:52:20');
# INSERT INTO holiday_info (id, date, name, wage, type, week, create_time) VALUES (63, '2024-07-14', '周日', null, null, null, '2024-10-30 12:52:20');
# INSERT INTO holiday_info (id, date, name, wage, type, week, create_time) VALUES (64, '2024-07-20', '周六', null, null, null, '2024-10-30 12:52:20');
# INSERT INTO holiday_info (id, date, name, wage, type, week, create_time) VALUES (65, '2024-07-21', '周日', null, null, null, '2024-10-30 12:52:20');
# INSERT INTO holiday_info (id, date, name, wage, type, week, create_time) VALUES (66, '2024-07-27', '周六', null, null, null, '2024-10-30 12:52:20');
# INSERT INTO holiday_info (id, date, name, wage, type, week, create_time) VALUES (67, '2024-07-28', '周日', null, null, null, '2024-10-30 12:52:20');
# INSERT INTO holiday_info (id, date, name, wage, type, week, create_time) VALUES (68, '2024-08-03', '周六', null, null, null, '2024-10-30 12:52:20');
# INSERT INTO holiday_info (id, date, name, wage, type, week, create_time) VALUES (69, '2024-08-04', '周日', null, null, null, '2024-10-30 12:52:20');
# INSERT INTO holiday_info (id, date, name, wage, type, week, create_time) VALUES (70, '2024-08-10', '周六', null, null, null, '2024-10-30 12:52:20');
# INSERT INTO holiday_info (id, date, name, wage, type, week, create_time) VALUES (71, '2024-08-11', '周日', null, null, null, '2024-10-30 12:52:20');
# INSERT INTO holiday_info (id, date, name, wage, type, week, create_time) VALUES (72, '2024-08-17', '周六', null, null, null, '2024-10-30 12:52:20');
# INSERT INTO holiday_info (id, date, name, wage, type, week, create_time) VALUES (73, '2024-08-18', '周日', null, null, null, '2024-10-30 12:52:20');
# INSERT INTO holiday_info (id, date, name, wage, type, week, create_time) VALUES (74, '2024-08-24', '周六', null, null, null, '2024-10-30 12:52:20');
# INSERT INTO holiday_info (id, date, name, wage, type, week, create_time) VALUES (75, '2024-08-25', '周日', null, null, null, '2024-10-30 12:52:20');
# INSERT INTO holiday_info (id, date, name, wage, type, week, create_time) VALUES (76, '2024-08-31', '周六', null, null, null, '2024-10-30 12:52:20');
# INSERT INTO holiday_info (id, date, name, wage, type, week, create_time) VALUES (77, '2024-09-01', '周日', null, null, null, '2024-10-30 12:52:20');
# INSERT INTO holiday_info (id, date, name, wage, type, week, create_time) VALUES (78, '2024-09-07', '周六', null, null, null, '2024-10-30 12:52:20');
# INSERT INTO holiday_info (id, date, name, wage, type, week, create_time) VALUES (79, '2024-09-08', '周日', null, null, null, '2024-10-30 12:52:20');
# INSERT INTO holiday_info (id, date, name, wage, type, week, create_time) VALUES (80, '2024-09-15', '中秋节', null, null, null, '2024-10-30 12:52:20');
# INSERT INTO holiday_info (id, date, name, wage, type, week, create_time) VALUES (81, '2024-09-16', '中秋节', null, null, null, '2024-10-30 12:52:20');
# INSERT INTO holiday_info (id, date, name, wage, type, week, create_time) VALUES (82, '2024-09-17', '中秋节', null, null, null, '2024-10-30 12:52:20');
# INSERT INTO holiday_info (id, date, name, wage, type, week, create_time) VALUES (83, '2024-09-21', '周六', null, null, null, '2024-10-30 12:52:20');
# INSERT INTO holiday_info (id, date, name, wage, type, week, create_time) VALUES (84, '2024-09-22', '周日', null, null, null, '2024-10-30 12:52:20');
# INSERT INTO holiday_info (id, date, name, wage, type, week, create_time) VALUES (85, '2024-09-28', '周六', null, null, null, '2024-10-30 12:52:20');
# INSERT INTO holiday_info (id, date, name, wage, type, week, create_time) VALUES (86, '2024-10-01', '国庆节', null, null, null, '2024-10-30 12:52:20');
# INSERT INTO holiday_info (id, date, name, wage, type, week, create_time) VALUES (87, '2024-10-02', '国庆节', null, null, null, '2024-10-30 12:52:20');
# INSERT INTO holiday_info (id, date, name, wage, type, week, create_time) VALUES (88, '2024-10-03', '国庆节', null, null, null, '2024-10-30 12:52:20');
# INSERT INTO holiday_info (id, date, name, wage, type, week, create_time) VALUES (89, '2024-10-04', '国庆节', null, null, null, '2024-10-30 12:52:20');
# INSERT INTO holiday_info (id, date, name, wage, type, week, create_time) VALUES (90, '2024-10-05', '国庆节', null, null, null, '2024-10-30 12:52:20');
# INSERT INTO holiday_info (id, date, name, wage, type, week, create_time) VALUES (91, '2024-10-06', '国庆节', null, null, null, '2024-10-30 12:52:20');
# INSERT INTO holiday_info (id, date, name, wage, type, week, create_time) VALUES (92, '2024-10-07', '国庆节', null, null, null, '2024-10-30 12:52:20');
# INSERT INTO holiday_info (id, date, name, wage, type, week, create_time) VALUES (93, '2024-10-13', '周日', null, null, null, '2024-10-30 12:52:20');
# INSERT INTO holiday_info (id, date, name, wage, type, week, create_time) VALUES (94, '2024-10-19', '周六', null, null, null, '2024-10-30 12:52:20');
# INSERT INTO holiday_info (id, date, name, wage, type, week, create_time) VALUES (95, '2024-10-20', '周日', null, null, null, '2024-10-30 12:52:20');
# INSERT INTO holiday_info (id, date, name, wage, type, week, create_time) VALUES (96, '2024-10-26', '周六', null, null, null, '2024-10-30 12:52:20');
# INSERT INTO holiday_info (id, date, name, wage, type, week, create_time) VALUES (97, '2024-10-27', '周日', null, null, null, '2024-10-30 12:52:20');
# INSERT INTO holiday_info (id, date, name, wage, type, week, create_time) VALUES (98, '2024-11-02', '周六', null, null, null, '2024-10-30 12:52:20');
# INSERT INTO holiday_info (id, date, name, wage, type, week, create_time) VALUES (99, '2024-11-03', '周日', null, null, null, '2024-10-30 12:52:20');
# INSERT INTO holiday_info (id, date, name, wage, type, week, create_time) VALUES (100, '2024-11-09', '周六', null, null, null, '2024-10-30 12:52:20');
# INSERT INTO holiday_info (id, date, name, wage, type, week, create_time) VALUES (101, '2024-11-10', '周日', null, null, null, '2024-10-30 12:52:20');
# INSERT INTO holiday_info (id, date, name, wage, type, week, create_time) VALUES (102, '2024-11-16', '周六', null, null, null, '2024-10-30 12:52:20');
# INSERT INTO holiday_info (id, date, name, wage, type, week, create_time) VALUES (103, '2024-11-17', '周日', null, null, null, '2024-10-30 12:52:20');
# INSERT INTO holiday_info (id, date, name, wage, type, week, create_time) VALUES (104, '2024-11-23', '周六', null, null, null, '2024-10-30 12:52:20');
# INSERT INTO holiday_info (id, date, name, wage, type, week, create_time) VALUES (105, '2024-11-24', '周日', null, null, null, '2024-10-30 12:52:20');
# INSERT INTO holiday_info (id, date, name, wage, type, week, create_time) VALUES (106, '2024-11-30', '周六', null, null, null, '2024-10-30 12:52:20');
# INSERT INTO holiday_info (id, date, name, wage, type, week, create_time) VALUES (107, '2024-12-01', '周日', null, null, null, '2024-10-30 12:52:20');
# INSERT INTO holiday_info (id, date, name, wage, type, week, create_time) VALUES (108, '2024-12-07', '周六', null, null, null, '2024-10-30 12:52:20');
# INSERT INTO holiday_info (id, date, name, wage, type, week, create_time) VALUES (109, '2024-12-08', '周日', null, null, null, '2024-10-30 12:52:20');
# INSERT INTO holiday_info (id, date, name, wage, type, week, create_time) VALUES (110, '2024-12-14', '周六', null, null, null, '2024-10-30 12:52:20');
# INSERT INTO holiday_info (id, date, name, wage, type, week, create_time) VALUES (111, '2024-12-15', '周日', null, null, null, '2024-10-30 12:52:20');
# INSERT INTO holiday_info (id, date, name, wage, type, week, create_time) VALUES (112, '2024-12-21', '周六', null, null, null, '2024-10-30 12:52:20');
# INSERT INTO holiday_info (id, date, name, wage, type, week, create_time) VALUES (113, '2024-12-22', '周日', null, null, null, '2024-10-30 12:52:20');
# INSERT INTO holiday_info (id, date, name, wage, type, week, create_time) VALUES (114, '2024-12-28', '周六', null, null, null, '2024-10-30 12:52:20');
# INSERT INTO holiday_info (id, date, name, wage, type, week, create_time) VALUES (115, '2024-12-29', '周日', null, null, null, '2024-10-30 12:52:20');
#
#
