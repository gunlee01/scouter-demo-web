DROP TABLE user;
CREATE TABLE user (
  user_id     VARCHAR(32) PRIMARY KEY,
  password    VARCHAR(16) NOT NULL,
  user_name   VARCHAR(32) NOT NULL,
  description VARCHAR(64),
  created     DATETIME NOT NULL
  );

CREATE INDEX idx_user_name on user(user_name);

DROP TABLE device;
CREATE TABLE device (
  device_id  VARCHAR(32) PRIMARY KEY,
  user_id    VARCHAR(32) NOT NULL,
  device_token VARCHAR(18) NOT NULL,
  created     DATETIME NOT NULL
  );

CREATE INDEX idx_device_user_id on device(user_id);

