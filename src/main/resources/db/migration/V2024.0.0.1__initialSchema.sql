DROP TABLE IF EXISTS url_mapping;

create table url_mapping
(
    `id` BIGINT(11) NOT NULL AUTO_INCREMENT,
    `url` VARCHAR(64) NOT NULL,
    `short_url` VARCHAR(64) NOT NULL UNIQUE,
    `expiration_date` TIMESTAMP,
    PRIMARY KEY (`id`)
);

create table counter
(
    `id` BIGINT(11) NOT NULL,
    `counter_number` BIGINT(11)
);

insert into counter (id, counter_number) values (1, 0);