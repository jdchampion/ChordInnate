DROP TABLE IF EXISTS CHORD_TYPE;

CREATE TABLE IF NOT EXISTS CHORD_TYPE (
  ID SERIAL PRIMARY KEY,
  SYMBOL VARCHAR(20) UNIQUE,
  RN_SYMBOL VARCHAR(20) NOT NULL,
  RN_CAPITAL INTEGER NOT NULL CHECK (RN_CAPITAL = 0 OR RN_CAPITAL = 1),
  RN_PRECEDENCE INTEGER NOT NULL DEFAULT 1 CHECK (RN_PRECEDENCE > 0),
  INTERVALS VARCHAR(100) NOT NULL, -- CHECK (INTERVALS REGEXP 'P1(, [mMPdA][0-9]+)+'),
  SIZE INTEGER,                     -- set by trigger
  CLASSIFICATION VARCHAR(20),       -- set by trigger
  CLASSIFICATION_ALT VARCHAR(20)    -- set by trigger
);

-- Automatically count the number of notes in the chord
-- Automatically classify the chord based on number of notes
DROP FUNCTION IF EXISTS TF_CTS;
CREATE FUNCTION TF_CTS() RETURNS TRIGGER AS $TF_CTS$
BEGIN
    NEW.SIZE = LENGTH(NEW.INTERVALS) - LENGTH(REPLACE(NEW.INTERVALS, ',', '')) + 1;
    NEW.CLASSIFICATION :=
            (CASE
                 WHEN NEW.SIZE = 1 THEN 'Monad'
                 WHEN NEW.SIZE = 2 THEN 'Dyad'
                 WHEN NEW.SIZE = 3 THEN 'Triad'
                 WHEN NEW.SIZE = 4 THEN 'Tetrad'
                 WHEN NEW.SIZE = 5 THEN 'Pentad'
                 WHEN NEW.SIZE = 6 THEN 'Hexad'
                 WHEN NEW.SIZE = 7 THEN 'Heptad'
                 WHEN NEW.SIZE = 8 THEN 'Octad'
                 WHEN NEW.SIZE = 9 THEN 'Ennead'
                 WHEN NEW.SIZE = 10 THEN 'Decad'
                END);

    NEW.CLASSIFICATION_ALT :=
            (CASE
                 WHEN NEW.SIZE = 1 THEN 'Monochord'
                 WHEN NEW.SIZE = 2 THEN 'Dichord'
                 WHEN NEW.SIZE = 3 THEN 'Trichord'
                 WHEN NEW.SIZE = 4 THEN 'Tetrachord'
                 WHEN NEW.SIZE = 5 THEN 'Pentachord'
                 WHEN NEW.SIZE = 6 THEN 'Hexachord'
                 WHEN NEW.SIZE = 7 THEN 'Heptachord'
                 WHEN NEW.SIZE = 8 THEN 'Octachord'
                 WHEN NEW.SIZE = 9 THEN 'Nonachord'
                 WHEN NEW.SIZE = 10 THEN 'Decachord'
                END);
    RETURN NEW;
END;
$TF_CTS$ LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS TRIG_CHORD_TYPE_CLASS ON CHORD_TYPE;
CREATE TRIGGER TRIG_CHORD_TYPE_CLASS BEFORE INSERT OR UPDATE ON CHORD_TYPE
FOR EACH ROW EXECUTE PROCEDURE TF_CTS();


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