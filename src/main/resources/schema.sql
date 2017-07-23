DROP TABLE user;
CREATE TABLE user (
  user_id     VARCHAR(32) PRIMARY KEY,
  user_name   VARCHAR(32) NOT NULL,
  description VARCHAR(64),
  created     DATETIME NOT NULL
  );

DROP TABLE device;
CREATE TABLE device (
  device_id  INTEGER PRIMARY KEY,
  user_id    VARCHAR(32) NOT NULL,
  device_token VARCHAR(18) NOT NULL,
  created     DATETIME NOT NULL
  );

