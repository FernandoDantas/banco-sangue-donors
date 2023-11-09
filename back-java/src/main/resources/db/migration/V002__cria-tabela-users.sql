CREATE TABLE IF NOT EXISTS users (
    users_id SERIAL CONSTRAINT pk_id_users PRIMARY KEY,
    users_email varchar(50) NOT NULL,
    crypted_pass varchar(700) not null,
    status_ varchar(50) not null default 'Não autorizado',
    users_name varchar(150),
    photo_url varchar(200),
    tenant int4 NOT NULL,
    UNIQUE(users_email)
);

ALTER TABLE users ADD CONSTRAINT users_tenant FOREIGN KEY (tenant) REFERENCES tenant(tenant_id) ON UPDATE CASCADE ON DELETE SET NULL;