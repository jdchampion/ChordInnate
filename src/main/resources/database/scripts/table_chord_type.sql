DROP TABLE IF EXISTS CHORD_TYPE;

CREATE TABLE IF NOT EXISTS CHORD_TYPE (
  ID INTEGER PRIMARY KEY AUTOINCREMENT,
  SYMBOL VARCHAR(20) UNIQUE,
  INTERVALS VARCHAR(100) NOT NULL, -- CHECK (INTERVALS REGEXP 'P1(, [mMPdA][0-9]+)+'),
  SIZE INTEGER,                     -- set by trigger
  CLASSIFICATION VARCHAR(20),       -- set by trigger
  CLASSIFICATION_ALT VARCHAR(20)    -- set by trigger
);

-- Automatically count the number of notes in the chord
DROP TRIGGER IF EXISTS TRIG_CHORD_TYPE_SIZE;
CREATE TRIGGER TRIG_CHORD_TYPE_SIZE AFTER INSERT ON CHORD_TYPE
BEGIN
  UPDATE CHORD_TYPE
  SET SIZE = LENGTH(INTERVALS) - LENGTH(REPLACE(INTERVALS, ',', '')) + 1
  WHERE _ROWID_ = LAST_INSERT_ROWID();
END;

-- Automatically classify the chord based on number of notes
DROP TRIGGER IF EXISTS TRIG_CHORD_TYPE_CLASS;
CREATE TRIGGER TRIG_CHORD_TYPE_CLASS AFTER UPDATE ON CHORD_TYPE
BEGIN
  UPDATE CHORD_TYPE
  SET CLASSIFICATION =
  (CASE
   WHEN SIZE = 1 THEN 'Monad'
   WHEN SIZE = 2 THEN 'Dyad'
   WHEN SIZE = 3 THEN 'Triad'
   WHEN SIZE = 4 THEN 'Tetrad'
   WHEN SIZE = 5 THEN 'Pentad'
   WHEN SIZE = 6 THEN 'Hexad'
   WHEN SIZE = 7 THEN 'Heptad'
   WHEN SIZE = 8 THEN 'Octad'
   WHEN SIZE = 9 THEN 'Ennead'
   WHEN SIZE = 10 THEN 'Decad'
   END);

  UPDATE CHORD_TYPE
  SET CLASSIFICATION_ALT =
  (CASE
   WHEN SIZE = 1 THEN 'Monochord'
   WHEN SIZE = 2 THEN 'Dichord'
   WHEN SIZE = 3 THEN 'Trichord'
   WHEN SIZE = 4 THEN 'Tetrachord'
   WHEN SIZE = 5 THEN 'Pentachord'
   WHEN SIZE = 6 THEN 'Hexachord'
   WHEN SIZE = 7 THEN 'Heptachord'
   WHEN SIZE = 8 THEN 'Octachord'
   WHEN SIZE = 9 THEN 'Nonachord'
   WHEN SIZE = 10 THEN 'Decachord'
   END);
END;

INSERT INTO CHORD_TYPE (SYMBOL, INTERVALS) VALUES
  ('maj', 'P1, M3, P5'),
  ('maj7', 'P1, M3, P5, M7'),
  ('7', 'P1, M3, P5, m7'),
  ('add9', 'P1, M3, P5, M9'),
  ('m', 'P1, m3, P5'),
  ('m7', 'P1, m3, P5, m7'),
  ('dim', 'P1, m3, d5'),
  ('dim7', 'P1, m3, d5, d7'),
  ('aug', 'P1, M3, A5'),
  ('sus4', 'P1, P4, P5'),
  ('sus2', 'P1, M2, P5'),
  ('maj9', 'P1, M3, P5, M7, M9'),
  ('maj13', 'P1, M3, P5, M7, M9, P11, M13'),
  ('maj9♯11', 'P1, M3, P5, M7, M9, A11'),
  ('maj13♯11', 'P1, M3, P5, M7, M9, A11, M13'),
  ('6', 'P1, M3, P5, M6'),
  ('6add9', 'P1, M3, P5, M6, M9'),
  ('maj7♯5', 'P1, M3, A5, M7'),
  ('maj7♭5', 'P1, M3, d5, M7'),
  ('m9', 'P1, m3, P5, m7, M9'),
  ('m11', 'P1, m3, P5, m7, M9, P11'),
  ('m13', 'P1, m3, P5, m7, M9, P11, M13'),
  ('m6', 'P1, m3, P5, M6'),
  ('madd9', 'P1, m3, P5, M9'),
  ('m6add9', 'P1, m3, P5, M6, M9'),
  ('mM7', 'P1, m3, P5, M7'),
  ('mM9', 'P1, m3, P5, M7, M9'),
  ('m7♭5', 'P1, m3, d5, m7'),
  ('ø', 'P1, m3, d5, m7'),
  ('m7♯5', 'P1, m3, A5, m7'),
  ('9', 'P1, M3, P5, m7, M9'),
  ('11', 'P1, M3, P5, m7, M9, P11'),
  ('13', 'P1, M3, P5, m7, M9, P11, M13'),
  ('7sus4', 'P1, P4, P5, m7'),
  ('7♭5', 'P1, M3, d5, m7'),
  ('7♯5', 'P1, M3, A5, m7'),
  ('7♭9', 'P1, M3, P5, m7, m9'),
  ('7♯9', 'P1, M3, P5, m7, A9'),
  ('7♭5♭9', 'P1, M3, d5, m7, m9'),
  ('7♭5♯9', 'P1, M3, d5, m7, A9'),
  ('7♯5♭9', 'P1, M3, A5, m7, m9'),
  ('7♯5♯9', 'P1, M3, A5, m7, A9'),
  ('9♭5', 'P1, M3, d5, m7, M9'),
  ('9♯5', 'P1, M3, A5, m7, M9'),
  ('13♯11', 'P1, M3, P5, m7, M9, A11, M13'),
  ('13♭9', 'P1, M3, P5, m7, m9, M13'),
  ('11♭9', 'P1, M3, P5, m7, m9, P11'),
  ('5', 'P1, P5'),
  ('sus2sus4', 'P1, M2, P4, P5'),
  ('-5', 'P1, M3, d5');