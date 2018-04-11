CREATE TABLE approvals(
  id VARCHAR(32) PRIMARY KEY,
  imageid VARCHAR(32) NOT NULL,
  status VARCHAR(10) NOT NULL,
  create_at BIGINT NOT NULL,
  update_at BIGINT NOT NULL
);