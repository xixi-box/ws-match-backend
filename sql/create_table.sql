-- auto-generated definition
create database user_center;
create table user
(
    id           bigint auto_increment comment '用户ID'
        primary key,
    userAccount  varchar(256)                       null comment '账号',
    avatarUrl    varchar(1024)                      null comment '头像',
    gender       tinyint                            null comment '性别',
    userPassword varchar(512)                       null comment '密码',
    phone        varchar(128)                       null comment '电话',
    email        varchar(256)                       null comment '邮箱',
    userStatus   int      default 0                 null comment '状态',
    createTime   datetime default CURRENT_TIMESTAMP null comment '创建时间',
    updateTime   datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete     tinyint  default 0                 null,
    username     varchar(256)                       null comment '用户名',
    userRole     int      default 0                 not null comment '0是普通用户，1是管理员用户'
)
    comment '用户表' engine = InnoDB;

