-- TODO: drop table and remove this script if not used
DROP TABLE IF EXISTS TBL_INTERVAL_DISTANCES;

CREATE TABLE IF NOT EXISTS TBL_INTERVAL_DISTANCES (
  ID INTEGER PRIMARY KEY AUTOINCREMENT,
  INTERVAL_ID INTEGER NOT NULL UNIQUE,
  DESCRIPTION VARCHAR(20) NOT NULL
);

INSERT INTO TBL_INTERVAL_DISTANCES (INTERVAL_ID, DESCRIPTION) VALUES
  (1, 'unison'),
  (2, 'second'),
  (3, 'third'),
  (4, 'fourth'),
  (5, 'fifth'),
  (6, 'sixth'),
  (7, 'seventh'),
  (8, 'octave'),
  (9, 'ninth'),
  (10, 'tenth'),
  (11, 'eleventh'),
  (12, 'twelfth'),
  (13, 'thirteenth'),
  (14, 'fourteenth'),
  (15, 'octave')
;