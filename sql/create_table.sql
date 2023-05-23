-- auto-generated definition
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
    userRole     int      default 0                 not null comment '0是普通用户，1是管理员用户',
    tags         varchar(1024)                      null comment '标签列表'
)
    comment '用户表';
alter table user add COLUMN tags varchar(1024) null comment '标签列表';

-- auto-generated definition
create table tag
(
    id         bigint auto_increment comment '用户ID'
        primary key,
    tagName    varchar(256)                       null comment '标签名',
    userId     bigint                             null comment '用户ID',
    parentId   bigint                             null comment '父标签',
    isParent   tinyint                            null comment '0不是 1是',
    createTime datetime default CURRENT_TIMESTAMP null comment '创建时间',
    updateTime datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete   tinyint  default 0                 null,
    constraint uniIdx_tagName
        unique (tagName)
)
    comment '用户表';

create index idx_userId
    on tag (userId);

