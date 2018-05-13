-- TODO: drop table and remove this script if not used
DROP TABLE IF EXISTS TBL_INTERVAL_QUALITIES;

CREATE TABLE IF NOT EXISTS TBL_INTERVAL_QUALITIES (
  ID INTEGER PRIMARY KEY AUTOINCREMENT,
  SYMBOL CHARACTER(1),
  DESCRIPTION VARCHAR(20) NOT NULL
);

INSERT INTO TBL_INTERVAL_QUALITIES (SYMBOL, DESCRIPTION) VALUES
  ('M', 'major'),
  ('m', 'minor'),
  ('P', 'perfect'),
  ('d', 'diminished'),
  ('A', 'augmented'),
  ('', 'doubly-diminished'),
  ('', 'doubly-augmented');