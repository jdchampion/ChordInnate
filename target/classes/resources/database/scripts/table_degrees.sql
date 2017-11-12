-- TODO: drop table and remove this script if not used
DROP TABLE IF EXISTS DEGREES;

CREATE TABLE IF NOT EXISTS DEGREES (
  ID INTEGER PRIMARY KEY AUTOINCREMENT,   -- for DB lookup
  DEG_ID INTEGER NOT NULL,  -- the numeric value of the degree
  DIATONIC_POSITION VARCHAR(20) NOT NULL CHECK (DIATONIC_POSITION <> ''), -- the degree's relative position to others in the scale
  MODAL_NAME VARCHAR (100) NOT NULL CHECK (MODAL_NAME <> ''), -- the modal name beginning on this degree
  RELATIVE_INDICATOR INTEGER NOT NULL CHECK (RELATIVE_INDICATOR = 1 OR RELATIVE_INDICATOR = 0) -- whether the scale containing this degree is a relative major (1) or minor (0)
);

INSERT INTO DEGREES (DEG_ID, DIATONIC_POSITION, MODAL_NAME, RELATIVE_INDICATOR) VALUES
  (1, 'tonic', 'Ionian', 1),
  (1, 'tonic', 'Aeolian', 0),
  (2, 'supertonic', 'Dorian', 1),
  (2, 'supertonic', 'Locrian', 0),
  (3, 'mediant', 'Phrygian', 1),
  (3, 'mediant', 'Ionian', 0),
  (4, 'subdominant', 'Lydian', 1),
  (4, 'subdominant', 'Dorian', 0),
  (5, 'dominant', 'Mixolydian', 1),
  (5, 'dominant', 'Phrygian', 0),
  (6, 'submediant', 'Aeolian', 1),
  (6, 'submediant', 'Lydian', 0),
  (7, 'leading tone|subtonic', 'Locrian', 1),
  (7, 'leading tone|subtonic', 'Mixolydian', 0),
  (8, 'tonic', 'Ionian', 1),
  (8, 'tonic', 'Aeolian', 0)
;