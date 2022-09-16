-- 菜单
create table `sys_menu`
(
    `menu_id`   bigint not null auto_increment,
    `parent_id` bigint comment '父菜单id，一级菜单为0',
    `name`      varchar(50) comment '菜单名称',
    `url`       varchar(200) comment '菜单url',
    `perms`     varchar(500) comment '授权(多个用逗号分隔，如：user:list,user:create)',
    `type`      int comment '类型   0：目录   1：菜单   2：按钮',
    `icon`      varchar(50) comment '菜单图标',
    `order_num` int comment '排序',
    primary key (`menu_id`)
) engine = `innodb`
  default character set utf8mb4 comment ='菜单管理';

-- 系统用户
create table `sys_user`
(
    `user_id`        bigint      not null auto_increment,
    `username`       varchar(50) not null comment '用户名',
    `password`       varchar(100) comment '密码',
    `salt`           varchar(20) comment '盐',
    `email`          varchar(100) comment '邮箱',
    `mobile`         varchar(100) comment '手机号',
    `status`         tinyint comment '状态  0：禁用   1：正常',
    `create_user_id` bigint(20) comment '创建者id',
    `create_time`    datetime comment '创建时间',
    primary key (`user_id`),
    unique index (`username`)
) engine = `innodb`
  default character set utf8mb4 comment ='系统用户';

-- 系统用户token
create table `sys_user_token`
(
    `user_id`     bigint(20)   not null,
    `token`       varchar(100) not null comment 'token',
    `expire_time` datetime default null comment '过期时间',
    `update_time` datetime default null comment '更新时间',
    primary key (`user_id`),
    unique key `token` (`token`)
) engine = `innodb`
  default character set utf8mb4 comment ='系统用户token';

-- 系统验证码
create table `sys_captcha`
(
    `uuid`        char(36)   not null comment 'uuid',
    `code`        varchar(6) not null comment '验证码',
    `expire_time` datetime default null comment '过期时间',
    primary key (`uuid`)
) engine = `innodb`
  default character set utf8mb4 comment ='系统验证码';

-- 角色
create table `sys_role`
(
    `role_id`        bigint not null auto_increment,
    `role_name`      varchar(100) comment '角色名称',
    `remark`         varchar(100) comment '备注',
    `create_user_id` bigint(20) comment '创建者id',
    `create_time`    datetime comment '创建时间',
    primary key (`role_id`)
) engine = `innodb`
  default character set utf8mb4 comment ='角色';

-- 用户与角色对应关系
create table `sys_user_role`
(
    `id`      bigint not null auto_increment,
    `user_id` bigint comment '用户id',
    `role_id` bigint comment '角色id',
    primary key (`id`)
) engine = `innodb`
  default character set utf8mb4 comment ='用户与角色对应关系';

-- 角色与菜单对应关系
create table `sys_role_menu`
(
    `id`      bigint not null auto_increment,
    `role_id` bigint comment '角色id',
    `menu_id` bigint comment '菜单id',
    primary key (`id`)
) engine = `innodb`
  default character set utf8mb4 comment ='角色与菜单对应关系';

-- 系统配置信息
create table `sys_config`
(
    `id`          bigint not null auto_increment,
    `param_key`   varchar(50) comment 'key',
    `param_value` varchar(2000) comment 'value',
    `status`      tinyint default 1 comment '状态   0：隐藏   1：显示',
    `remark`      varchar(500) comment '备注',
    primary key (`id`),
    unique index (`param_key`)
) engine = `innodb`
  default character set utf8mb4 comment ='系统配置信息表';


-- 系统日志
create table `sys_log`
(
    `id`          bigint(20) not null auto_increment,
    `username`    varchar(50) comment '用户名',
    `operation`   varchar(50) comment '用户操作',
    `method`      varchar(200) comment '请求方法',
    `params`      varchar(5000) comment '请求参数',
    `time`        bigint     not null comment '执行时长(毫秒)',
    `ip`          varchar(64) comment 'ip地址',
    `create_date` datetime comment '创建时间',
    primary key (`id`)
) engine = `innodb`
  default character set utf8mb4 comment ='系统日志';


-- 文件上传
create table `sys_oss`
(
    `id`          bigint(20) not null auto_increment,
    `url`         varchar(200) comment 'url地址',
    `create_date` datetime comment '创建时间',
    primary key (`id`)
) engine = `innodb`
  default character set utf8mb4 comment ='文件上传';


-- 定时任务
create table `schedule_job`
(
    `job_id`          bigint(20) not null auto_increment comment '任务id',
    `bean_name`       varchar(200)  default null comment 'spring bean名称',
    `params`          varchar(2000) default null comment '参数',
    `cron_expression` varchar(100)  default null comment 'cron表达式',
    `status`          tinyint(4)    default null comment '任务状态  0：正常  1：暂停',
    `remark`          varchar(255)  default null comment '备注',
    `create_time`     datetime      default null comment '创建时间',
    primary key (`job_id`)
) engine = `innodb`
  default character set utf8mb4 comment ='定时任务';

-- 定时任务日志
create table `schedule_job_log`
(
    `log_id`      bigint(20) not null auto_increment comment '任务日志id',
    `job_id`      bigint(20) not null comment '任务id',
    `bean_name`   varchar(200)  default null comment 'spring bean名称',
    `params`      varchar(2000) default null comment '参数',
    `status`      tinyint(4) not null comment '任务状态    0：成功    1：失败',
    `error`       varchar(2000) default null comment '失败信息',
    `times`       int(11)    not null comment '耗时(单位：毫秒)',
    `create_time` datetime      default null comment '创建时间',
    primary key (`log_id`),
    key `job_id` (`job_id`)
) engine = `innodb`
  default character set utf8mb4 comment ='定时任务日志';


-- 用户表
create table `tb_user`
(
    `user_id`     bigint      not null auto_increment,
    `username`    varchar(50) not null comment '用户名',
    `mobile`      varchar(20) not null comment '手机号',
    `password`    varchar(64) comment '密码',
    `create_time` datetime comment '创建时间',
    primary key (`user_id`),
    unique index (`username`)
) engine = `innodb`
  default character set utf8mb4 comment ='用户';


-- 初始数据
insert into `sys_user` (`user_id`, `username`, `password`, `salt`, `email`, `mobile`, `status`, `create_user_id`,
                        `create_time`)
values ('1', 'admin', '9ec9750e709431dad22365cabc5c625482e574c74adaebba7dd02f1129e4ce1d', 'yzcmcznvbxocrsz9dm8e',
        'root@renren.io', '13612345678', '1', '1', '2016-11-11 11:11:11');

insert into `sys_menu`(`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
values (1, 0, '系统管理', null, null, 0, 'system', 0);
insert into `sys_menu`(`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
values (2, 1, '管理员列表', 'sys/user', null, 1, 'admin', 1);
insert into `sys_menu`(`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
values (3, 1, '角色管理', 'sys/role', null, 1, 'role', 2);
insert into `sys_menu`(`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
values (4, 1, '菜单管理', 'sys/menu', null, 1, 'menu', 3);
insert into `sys_menu`(`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
values (5, 1, 'sql监控', 'http://localhost:8080/renren-fast/druid/sql.html', null, 1, 'sql', 4);
insert into `sys_menu`(`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
values (6, 1, '定时任务', 'job/schedule', null, 1, 'job', 5);
insert into `sys_menu`(`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
values (7, 6, '查看', null, 'sys:schedule:list,sys:schedule:info', 2, null, 0);
insert into `sys_menu`(`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
values (8, 6, '新增', null, 'sys:schedule:save', 2, null, 0);
insert into `sys_menu`(`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
values (9, 6, '修改', null, 'sys:schedule:update', 2, null, 0);
insert into `sys_menu`(`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
values (10, 6, '删除', null, 'sys:schedule:delete', 2, null, 0);
insert into `sys_menu`(`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
values (11, 6, '暂停', null, 'sys:schedule:pause', 2, null, 0);
insert into `sys_menu`(`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
values (12, 6, '恢复', null, 'sys:schedule:resume', 2, null, 0);
insert into `sys_menu`(`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
values (13, 6, '立即执行', null, 'sys:schedule:run', 2, null, 0);
insert into `sys_menu`(`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
values (14, 6, '日志列表', null, 'sys:schedule:log', 2, null, 0);
insert into `sys_menu`(`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
values (15, 2, '查看', null, 'sys:user:list,sys:user:info', 2, null, 0);
insert into `sys_menu`(`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
values (16, 2, '新增', null, 'sys:user:save,sys:role:select', 2, null, 0);
insert into `sys_menu`(`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
values (17, 2, '修改', null, 'sys:user:update,sys:role:select', 2, null, 0);
insert into `sys_menu`(`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
values (18, 2, '删除', null, 'sys:user:delete', 2, null, 0);
insert into `sys_menu`(`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
values (19, 3, '查看', null, 'sys:role:list,sys:role:info', 2, null, 0);
insert into `sys_menu`(`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
values (20, 3, '新增', null, 'sys:role:save,sys:menu:list', 2, null, 0);
insert into `sys_menu`(`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
values (21, 3, '修改', null, 'sys:role:update,sys:menu:list', 2, null, 0);
insert into `sys_menu`(`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
values (22, 3, '删除', null, 'sys:role:delete', 2, null, 0);
insert into `sys_menu`(`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
values (23, 4, '查看', null, 'sys:menu:list,sys:menu:info', 2, null, 0);
insert into `sys_menu`(`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
values (24, 4, '新增', null, 'sys:menu:save,sys:menu:select', 2, null, 0);
insert into `sys_menu`(`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
values (25, 4, '修改', null, 'sys:menu:update,sys:menu:select', 2, null, 0);
insert into `sys_menu`(`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
values (26, 4, '删除', null, 'sys:menu:delete', 2, null, 0);
insert into `sys_menu`(`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
values (27, 1, '参数管理', 'sys/config',
        'sys:config:list,sys:config:info,sys:config:save,sys:config:update,sys:config:delete', 1, 'config', 6);
insert into `sys_menu`(`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
values (29, 1, '系统日志', 'sys/log', 'sys:log:list', 1, 'log', 7);
insert into `sys_menu`(`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
values (30, 1, '文件上传', 'oss/oss', 'sys:oss:all', 1, 'oss', 6);

insert into `sys_config` (`param_key`, `param_value`, `status`, `remark`)
values ('cloud_storage_config_key',
        '{\"aliyunaccesskeyid\":\"\",\"aliyunaccesskeysecret\":\"\",\"aliyunbucketname\":\"\",\"aliyundomain\":\"\",\"aliyunendpoint\":\"\",\"aliyunprefix\":\"\",\"qcloudbucketname\":\"\",\"qclouddomain\":\"\",\"qcloudprefix\":\"\",\"qcloudsecretid\":\"\",\"qcloudsecretkey\":\"\",\"qiniuaccesskey\":\"nrgmfabzxwlo5b-yysjoe8-az1eisdi1z3ubloez\",\"qiniubucketname\":\"ios-app\",\"qiniudomain\":\"http://7xqbwh.dl1.z0.glb.clouddn.com\",\"qiniuprefix\":\"upload\",\"qiniusecretkey\":\"uiwjhevmrwu0vlxfvgy0tacodgqasdtvljkdy6vv\",\"type\":1}',
        '0', '云存储配置信息');
insert into `schedule_job` (`bean_name`, `params`, `cron_expression`, `status`, `remark`, `create_time`)
values ('testtask', 'renren', '0 0/30 * * * ?', '0', '参数测试', now());


-- 账号：13612345678  密码：admin
insert into `tb_user` (`username`, `mobile`, `password`, `create_time`)
values ('mark', '13612345678', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918',
        '2017-03-23 22:37:41');


--  quartz自带表结构
create table qrtz_job_details
(
    sched_name        varchar(120) not null,
    job_name          varchar(200) not null,
    job_group         varchar(200) not null,
    description       varchar(250) null,
    job_class_name    varchar(250) not null,
    is_durable        varchar(1)   not null,
    is_nonconcurrent  varchar(1)   not null,
    is_update_data    varchar(1)   not null,
    requests_recovery varchar(1)   not null,
    job_data          blob         null,
    primary key (sched_name, job_name, job_group)
)
    engine = innodb
    default charset = utf8;

create table qrtz_triggers
(
    sched_name     varchar(120) not null,
    trigger_name   varchar(200) not null,
    trigger_group  varchar(200) not null,
    job_name       varchar(200) not null,
    job_group      varchar(200) not null,
    description    varchar(250) null,
    next_fire_time bigint(13)   null,
    prev_fire_time bigint(13)   null,
    priority       integer      null,
    trigger_state  varchar(16)  not null,
    trigger_type   varchar(8)   not null,
    start_time     bigint(13)   not null,
    end_time       bigint(13)   null,
    calendar_name  varchar(200) null,
    misfire_instr  smallint(2)  null,
    job_data       blob         null,
    primary key (sched_name, trigger_name, trigger_group),
    foreign key (sched_name, job_name, job_group)
        references qrtz_job_details (sched_name, job_name, job_group)
)
    engine = innodb
    default charset = utf8;

create table qrtz_simple_triggers
(
    sched_name      varchar(120) not null,
    trigger_name    varchar(200) not null,
    trigger_group   varchar(200) not null,
    repeat_count    bigint(7)    not null,
    repeat_interval bigint(12)   not null,
    times_triggered bigint(10)   not null,
    primary key (sched_name, trigger_name, trigger_group),
    foreign key (sched_name, trigger_name, trigger_group)
        references qrtz_triggers (sched_name, trigger_name, trigger_group)
)
    engine = innodb
    default charset = utf8;

create table qrtz_cron_triggers
(
    sched_name      varchar(120) not null,
    trigger_name    varchar(200) not null,
    trigger_group   varchar(200) not null,
    cron_expression varchar(120) not null,
    time_zone_id    varchar(80),
    primary key (sched_name, trigger_name, trigger_group),
    foreign key (sched_name, trigger_name, trigger_group)
        references qrtz_triggers (sched_name, trigger_name, trigger_group)
)
    engine = innodb
    default charset = utf8;

create table qrtz_simprop_triggers
(
    sched_name    varchar(120)   not null,
    trigger_name  varchar(200)   not null,
    trigger_group varchar(200)   not null,
    str_prop_1    varchar(512)   null,
    str_prop_2    varchar(512)   null,
    str_prop_3    varchar(512)   null,
    int_prop_1    int            null,
    int_prop_2    int            null,
    long_prop_1   bigint         null,
    long_prop_2   bigint         null,
    dec_prop_1    numeric(13, 4) null,
    dec_prop_2    numeric(13, 4) null,
    bool_prop_1   varchar(1)     null,
    bool_prop_2   varchar(1)     null,
    primary key (sched_name, trigger_name, trigger_group),
    foreign key (sched_name, trigger_name, trigger_group)
        references qrtz_triggers (sched_name, trigger_name, trigger_group)
)
    engine = innodb
    default charset = utf8;

create table qrtz_blob_triggers
(
    sched_name    varchar(120) not null,
    trigger_name  varchar(200) not null,
    trigger_group varchar(200) not null,
    blob_data     blob         null,
    primary key (sched_name, trigger_name, trigger_group),
    index (sched_name, trigger_name, trigger_group),
    foreign key (sched_name, trigger_name, trigger_group)
        references qrtz_triggers (sched_name, trigger_name, trigger_group)
)
    engine = innodb
    default charset = utf8;

create table qrtz_calendars
(
    sched_name    varchar(120) not null,
    calendar_name varchar(200) not null,
    calendar      blob         not null,
    primary key (sched_name, calendar_name)
)
    engine = innodb
    default charset = utf8;

create table qrtz_paused_trigger_grps
(
    sched_name    varchar(120) not null,
    trigger_group varchar(200) not null,
    primary key (sched_name, trigger_group)
)
    engine = innodb
    default charset = utf8;

create table qrtz_fired_triggers
(
    sched_name        varchar(120) not null,
    entry_id          varchar(95)  not null,
    trigger_name      varchar(200) not null,
    trigger_group     varchar(200) not null,
    instance_name     varchar(200) not null,
    fired_time        bigint(13)   not null,
    sched_time        bigint(13)   not null,
    priority          integer      not null,
    state             varchar(16)  not null,
    job_name          varchar(200) null,
    job_group         varchar(200) null,
    is_nonconcurrent  varchar(1)   null,
    requests_recovery varchar(1)   null,
    primary key (sched_name, entry_id)
)
    engine = innodb
    default charset = utf8;

create table qrtz_scheduler_state
(
    sched_name        varchar(120) not null,
    instance_name     varchar(200) not null,
    last_checkin_time bigint(13)   not null,
    checkin_interval  bigint(13)   not null,
    primary key (sched_name, instance_name)
)
    engine = innodb
    default charset = utf8;

create table qrtz_locks
(
    sched_name varchar(120) not null,
    lock_name  varchar(40)  not null,
    primary key (sched_name, lock_name)
)
    engine = innodb
    default charset = utf8;

create index idx_qrtz_j_req_recovery on qrtz_job_details (sched_name, requests_recovery);
create index idx_qrtz_j_grp on qrtz_job_details (sched_name, job_group);

create index idx_qrtz_t_j on qrtz_triggers (sched_name, job_name, job_group);
create index idx_qrtz_t_jg on qrtz_triggers (sched_name, job_group);
create index idx_qrtz_t_c on qrtz_triggers (sched_name, calendar_name);
create index idx_qrtz_t_g on qrtz_triggers (sched_name, trigger_group);
create index idx_qrtz_t_state on qrtz_triggers (sched_name, trigger_state);
create index idx_qrtz_t_n_state on qrtz_triggers (sched_name, trigger_name, trigger_group, trigger_state);
create index idx_qrtz_t_n_g_state on qrtz_triggers (sched_name, trigger_group, trigger_state);
create index idx_qrtz_t_next_fire_time on qrtz_triggers (sched_name, next_fire_time);
create index idx_qrtz_t_nft_st on qrtz_triggers (sched_name, trigger_state, next_fire_time);
create index idx_qrtz_t_nft_misfire on qrtz_triggers (sched_name, misfire_instr, next_fire_time);
create index idx_qrtz_t_nft_st_misfire on qrtz_triggers (sched_name, misfire_instr, next_fire_time, trigger_state);
create index idx_qrtz_t_nft_st_misfire_grp on qrtz_triggers (sched_name, misfire_instr, next_fire_time, trigger_group,
                                                             trigger_state);

create index idx_qrtz_ft_trig_inst_name on qrtz_fired_triggers (sched_name, instance_name);
create index idx_qrtz_ft_inst_job_req_rcvry on qrtz_fired_triggers (sched_name, instance_name, requests_recovery);
create index idx_qrtz_ft_j_g on qrtz_fired_triggers (sched_name, job_name, job_group);
create index idx_qrtz_ft_jg on qrtz_fired_triggers (sched_name, job_group);
create index idx_qrtz_ft_t_g on qrtz_fired_triggers (sched_name, trigger_name, trigger_group);
create index idx_qrtz_ft_tg on qrtz_fired_triggers (sched_name, trigger_group);
