DROP TABLE IF EXISTS CHORD_TYPE;

CREATE TABLE IF NOT EXISTS CHORD_TYPE (
  ID INTEGER PRIMARY KEY AUTOINCREMENT,
  SYMBOL VARCHAR(20) UNIQUE,
  RN_SYMBOL VARCHAR(20) NOT NULL,
  RN_CAPITAL INTEGER(1) NOT NULL CHECK (RN_CAPITAL = 0 OR RN_CAPITAL = 1),
  RN_PRECEDENCE INTEGER NOT NULL DEFAULT 1 CHECK (RN_PRECEDENCE > 0),
  INTERVALS VARCHAR(100) NOT NULL, -- CHECK (INTERVALS REGEXP 'P1(, [mMPdA][0-9]+)+'),
  SIZE INTEGER                     -- set by trigger
);

-- Automatically count the number of notes in the chord
DROP TRIGGER IF EXISTS TRIG_CHORD_TYPE_SIZE;
CREATE TRIGGER TRIG_CHORD_TYPE_SIZE AFTER INSERT ON CHORD_TYPE
BEGIN
  UPDATE CHORD_TYPE
  SET SIZE = LENGTH(INTERVALS) - LENGTH(REPLACE(INTERVALS, ',', '')) + 1
  WHERE _ROWID_ = LAST_INSERT_ROWID();
END;

INSERT INTO CHORD_TYPE (SYMBOL, RN_SYMBOL, RN_CAPITAL, RN_PRECEDENCE, INTERVALS) VALUES
  ('maj', '', 1, 1, 'P1, M3, P5'),
  ('maj7', '', 0, 1, 'P1, M3, P5, M7'),
  ('7', '7', 1, 1, 'P1, M3, P5, m7'),
  ('add9', 'add9', 1, 1, 'P1, M3, P5, M9'),
  ('m', '', 0, 1, 'P1, m3, P5'),
  ('m7', '7', 0, 1, 'P1, m3, P5, m7'),
  ('dim', '˚', 0, 1, 'P1, m3, d5'),
  ('dim7', '˚7', 0, 1, 'P1, m3, d5, d7'),
  ('aug', '+', 1, 1, 'P1, M3, A5'),
  ('sus4', 'sus4', 1, 1, 'P1, P4, P5'),
  ('sus2', 'sus2', 1, 1, 'P1, M2, P5'),
  ('maj9', '9', 1, 1, 'P1, M3, P5, M7, M9'),
  ('maj13', '13', 1, 1, 'P1, M3, P5, M7, M9, P11, M13'),
  ('maj9♯11', '9♯11', 1, 1, 'P1, M3, P5, M7, M9, A11'),
  ('maj13♯11', '13♯11', 1, 1, 'P1, M3, P5, M7, M9, A11, M13'),
  ('6', '6', 1, 1, 'P1, M3, P5, M6'),
  ('6add9', '6add9', 1, 1, 'P1, M3, P5, M6, M9'),
  ('maj7♯5', '7♯5', 1, 1, 'P1, M3, A5, M7'),
  ('maj7♭5', '7♭5', 1, 1, 'P1, M3, d5, M7'),
  ('m9', '9', 0, 1, 'P1, m3, P5, m7, M9'),
  ('m11', '11', 0, 1, 'P1, m3, P5, m7, M9, P11'),
  ('m13', '13', 0, 1, 'P1, m3, P5, m7, M9, P11, M13'),
  ('m6', '6', 0, 1, 'P1, m3, P5, M6'),
  ('madd9', 'add9', 0, 1, 'P1, m3, P5, M9'),
  ('m6add9', '6add9', 0, 1, 'P1, m3, P5, M6, M9'),
  ('mM7', '7', 0, 1, 'P1, m3, P5, M7'),
  ('mM9', '9', 0, 1, 'P1, m3, P5, M7, M9'),
  ('m7♭5', 'ø7', 0, 2, 'P1, m3, d5, m7'),
  ('ø7', 'ø7', 0, 1, 'P1, m3, d5, m7'),
  ('m7♯5', '7♯5', 0, 1, 'P1, m3, A5, m7'),
  ('9', '9', 1, 1, 'P1, M3, P5, m7, M9'),
  ('11', '11', 1, 1, 'P1, M3, P5, m7, M9, P11'),
  ('13', '13', 1, 1, 'P1, M3, P5, m7, M9, P11, M13'),
  ('7sus4', '7sus4', 1, 1, 'P1, P4, P5, m7'),
  ('7♭5', '7♭5', 1, 1, 'P1, M3, d5, m7'),
  ('7♯5', '7♯5', 1, 1, 'P1, M3, A5, m7'),
  ('7♭9', '7♭9', 1, 1, 'P1, M3, P5, m7, m9'),
  ('7♯9', '7♯9', 1, 1, 'P1, M3, P5, m7, A9'),
  ('7♭5♭9', '7♭5♭9', 1, 1, 'P1, M3, d5, m7, m9'),
  ('7♭5♯9', '7♭5♯9', 1, 1, 'P1, M3, d5, m7, A9'),
  ('7♯5♭9', '7♯5♭9', 1, 1, 'P1, M3, A5, m7, m9'),
  ('7♯5♯9', '7♯5♯9', 1, 1, 'P1, M3, A5, m7, A9'),
  ('9♭5', '9♭5', 1, 1, 'P1, M3, d5, m7, M9'),
  ('9♯5', '9♯5', 1, 1, 'P1, M3, A5, m7, M9'),
  ('13♯11', '13♯11', 1, 1, 'P1, M3, P5, m7, M9, A11, M13'),
  ('13♭9', '13♭9', 1, 1, 'P1, M3, P5, m7, m9, M13'),
  ('11♭9', '11♭9', 1, 1, 'P1, M3, P5, m7, m9, P11'),
  ('5', 'no3', 1, 1, 'P1, P5'),
  ('sus2sus4', 'sus2sus4', 1, 1, 'P1, M2, P4, P5'),
  ('-5', '-5', 1, 1, 'P1, M3, d5');