CREATE TABLE IF NOT EXISTS tenant (
    tenant_id SERIAL CONSTRAINT pk_id_tenant PRIMARY KEY NOT NULL,
    tenant_name varchar(150),
    status_ varchar(50) not null default 'Não autorizado'
);