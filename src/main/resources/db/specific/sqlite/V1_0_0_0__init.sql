PRAGMA foreign_keys = ON; -- allow foreign key constraints to be used

------------------------------------------------------------------
--                          Tags                                --
------------------------------------------------------------------

DROP TABLE IF EXISTS SCALE_TYPE_TAG;
DROP TABLE IF EXISTS CHORD_TYPE_TAG;
DROP TABLE IF EXISTS TAG;

CREATE TABLE IF NOT EXISTS TAG(
                                  ID INTEGER PRIMARY KEY,
                                  NAME VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE SCALE_TYPE_TAG(
                               ID       INTEGER PRIMARY KEY,
                               SCALE_TYPE_ID INTEGER NOT NULL,
                               TAG_ID   INTEGER NOT NULL,
                               FOREIGN KEY (SCALE_TYPE_ID) REFERENCES SCALE_TYPE (ID) ON UPDATE CASCADE ON DELETE CASCADE,
                               FOREIGN KEY (TAG_ID) REFERENCES TAG (ID) ON UPDATE CASCADE ON DELETE CASCADE,
                               UNIQUE (SCALE_TYPE_ID, TAG_ID) -- A given tag can only have one relation to a scale type, and vice-versa
);

CREATE TABLE CHORD_TYPE_TAG(
                               ID       INTEGER PRIMARY KEY,
                               CHORD_TYPE_ID INTEGER NOT NULL,
                               TAG_ID   INTEGER NOT NULL,
                               FOREIGN KEY (CHORD_TYPE_ID) REFERENCES CHORD_TYPE (ID) ON UPDATE CASCADE ON DELETE CASCADE,
                               FOREIGN KEY (TAG_ID) REFERENCES TAG (ID) ON UPDATE CASCADE ON DELETE CASCADE,
                               UNIQUE (CHORD_TYPE_ID, TAG_ID) -- A given tag can only have one relation to a chord type, and vice-versa
);


------------------------------------------------------------------
--                          Chord Type                          --
------------------------------------------------------------------

DROP TRIGGER IF EXISTS TRIG_INSERT_CHORD;
-- DROP TRIGGER IF EXISTS TRIG_CHORD_TYPE_NO_UPDATE_PRESETS;
-- DROP TRIGGER IF EXISTS TRIG_CHORD_TYPE_NO_DELETE_PRESETS;

DROP TABLE IF EXISTS CHORD_TYPE;

CREATE TABLE IF NOT EXISTS CHORD_TYPE (
                                          ID INTEGER PRIMARY KEY AUTOINCREMENT,
                                          SYMBOL VARCHAR(20) UNIQUE CHECK (SYMBOL <> ''),
                                          RN_SYMBOL VARCHAR(20) NOT NULL,
                                          RN_CAPITAL INTEGER(1) NOT NULL CHECK (RN_CAPITAL = 0 OR RN_CAPITAL = 1),
                                          RN_PRECEDENCE INTEGER NOT NULL DEFAULT 1 CHECK (RN_PRECEDENCE > 0),
                                          INTERVALS VARCHAR(100) NOT NULL, -- CHECK (INTERVALS REGEXP 'P1(, [mMPdA][0-9]+)+'),
                                          SIZE INTEGER,                    -- set by trigger
                                          PRESET INTEGER(1) NOT NULL DEFAULT 0 CHECK (PRESET = 0 OR PRESET = 1)
);

-- Automatically count the number of notes in the chord
CREATE TRIGGER TRIG_INSERT_CHORD AFTER INSERT ON CHORD_TYPE
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

-- Prevent user from editing preset scale data (except for tags)
UPDATE CHORD_TYPE SET PRESET = 1;

-- CREATE TRIGGER TRIG_CHORD_TYPE_NO_UPDATE_PRESETS BEFORE UPDATE ON CHORD_TYPE
-- BEGIN
--     SELECT
--         (CASE
--              WHEN OLD.PRESET = 1 AND (NEW.SYMBOL <> OLD.SYMBOL OR NEW.RN_SYMBOL <> OLD.RN_SYMBOL OR NEW.RN_CAPITAL <> OLD.RN_CAPITAL OR NEW.RN_PRECEDENCE <> OLD.RN_PRECEDENCE OR NEW.INTERVALS <> OLD.INTERVALS OR NEW.SIZE <> OLD.SIZE OR NEW.PRESET <> OLD.PRESET)
--                  THEN RAISE(ABORT, 'Cannot change fields on preset chord types')
--             END)
--     FROM CHORD_TYPE;
-- END;

-- CREATE TRIGGER TRIG_CHORD_TYPE_NO_DELETE_PRESETS BEFORE DELETE ON CHORD_TYPE
-- BEGIN
--     SELECT
--         (CASE
--              WHEN OLD.PRESET = 1
--                  THEN RAISE(ABORT, 'Cannot delete preset chord types')
--             END)
--     FROM CHORD_TYPE;
-- END;



------------------------------------------------------------------
--                          Scale Type                          --
------------------------------------------------------------------




DROP TRIGGER IF EXISTS TRIG_SCALE_TYPE_MATCHING_NAME_1;
DROP TRIGGER IF EXISTS TRIG_SCALE_TYPE_MATCHING_NAME_2;
DROP TRIGGER IF EXISTS TRIG_INSERT_SCALE;
-- DROP TRIGGER IF EXISTS TRIG_SCALE_TYPE_NO_UPDATE_PRESETS;
-- DROP TRIGGER IF EXISTS TRIG_SCALE_TYPE_NO_DELETE_PRESETS;


DROP TABLE IF EXISTS SCALE_TYPE;

CREATE TABLE IF NOT EXISTS SCALE_TYPE (
                                          ID INTEGER PRIMARY KEY AUTOINCREMENT,
                                          NAME VARCHAR(100) NOT NULL UNIQUE CHECK (NAME <> ''),
                                          INTERVALS VARCHAR(100) NOT NULL, -- CHECK (INTERVALS REGEXP 'P1(, [mMPdA][0-9]+)+'), TODO: use GLOB wildcards?
                                          SIZE INTEGER,										-- set by trigger
                                          ORIGIN INTEGER,
                                          PRESET INTEGER(1) NOT NULL DEFAULT 0 CHECK (PRESET = 0 OR PRESET = 1)
);

-- Do not allow any duplicate scale names to be added to this table
-- (case and whitespace are factored out in the dupe check)
CREATE TRIGGER TRIG_SCALE_TYPE_MATCHING_NAME_1 BEFORE INSERT ON SCALE_TYPE
BEGIN
    SELECT
        (CASE
             WHEN (SELECT COUNT(*) FROM SCALE_TYPE s WHERE TRIM(s.NAME) = TRIM(new.NAME) COLLATE NOCASE AND s.ID <> new.ID) > 0
                 THEN RAISE(ABORT, 'Another scale by the same name already exists')
            END)
    FROM SCALE_TYPE;
END;
CREATE TRIGGER TRIG_SCALE_TYPE_MATCHING_NAME_2 BEFORE UPDATE ON SCALE_TYPE
BEGIN
    SELECT
        (CASE
             WHEN (SELECT COUNT(*) FROM SCALE_TYPE s WHERE TRIM(s.NAME) = TRIM(new.NAME) COLLATE NOCASE AND s.ID <> new.ID) > 0
                 THEN RAISE(ABORT, 'Another scale by the same name already exists')
            END)
    FROM SCALE_TYPE;
END;

-- Set up the rest of the DB internals on first insert
CREATE TRIGGER TRIG_INSERT_SCALE AFTER INSERT ON SCALE_TYPE
BEGIN
    UPDATE SCALE_TYPE
    SET SIZE = LENGTH(INTERVALS) - LENGTH(REPLACE(INTERVALS, ',', '')) + 1
    WHERE _ROWID_ = LAST_INSERT_ROWID();
END;

INSERT INTO SCALE_TYPE (NAME, INTERVALS, ORIGIN) VALUES
('Acoustic', 'P1, M2, M3, A4, P5, M6, m7', NULL),
('Adonai Malakh', 'P1, m2, M2, m3, P4, P5, M6, m7', 376), -- Jewish
('Aeolian', 'P1, M2, m3, P4, P5, m6, m7', 300),
('Aeolian Harmonic', 'P1, A2, M3, A4, P5, M6, M7', 300),
('Aeolian Major', 'P1, M2, M3, P4, P5, m6, m7', 300),
('Aeolian Pentatonic', 'P1, m3, P4, P5, m7', 300),
('Aeolian b1', 'P1, A2, M3, A4, A5, M6, M7', 300),
('Ahava Rabba', 'P1, m2, A2, M3, P4, d5, m6, m7', 376), -- Jewish
('Akebono I', 'P1, M2, m3, P5, M6', 392),
('Akebono II', 'P1, m2, P4, P5, m6', 392),
('Algerian', 'P1, M2, m3, P4, A4, P5, m6, M7', 682),
('Alhijaz', 'P1, m2, M3, P4, P5, m6, m7', 682),
('Altered Diminished', 'P1, m2, m3, d4, d5, m6, d7', NULL),
('Altered Dominant', 'P1, m2, m3, d4, d5, m6, m7', NULL),
('Altered Lydian', 'P1, M2, M3, A4, A5, M6, M7', NULL),
('Altered Mixolydian I', 'P1, M2, M3, P4, P5, A5, M6, M7', NULL),
('Altered Mixolydian II', 'P1, M2, M3, P4, P5, m6, M6, M7', NULL),
('Altered Pentatonic', 'P1, m2, P4, P5, M6', NULL),
('Ambassel', 'P1, m2, P4, P5, m6', 230),
('Ancient Chinese', 'P1, M2, M3, A4, P5, M6', 156),
('Anhemitonic Hexatonic', 'P1, M2, M3, A4, A5, A6', NULL),
('Arabic', 'P1, m2, M3, P4, P5, m6, m7', 682),
('Ararai', 'P1, M2, M3, P4, P5, M6, M7', 230),
('Arezzo Major Diatonic Hexachord', 'P1, M2, M3, P4, P5, M6', 380),
('Asawari Thaat', 'P1, M2, m3, P4, P5, m6, m7', 356),
('Augmented Hexatonic', 'P1, A2, M3, P5, m6, M7', NULL),
('Auxiliary Diminished', 'P1, M2, m3, P4, d5, m6, M6, M7', NULL),
('Bacovia', 'P1, M3, P4, m6, M7', 642),
('Balinese Pelog', 'P1, m2, m3, P5, m6', 360), -- Balinese
('Banshikicho', 'P1, M2, m3, d4, P5, M6, m7', 392),
('Bartok', 'P1, M2, M3, A4, P5, M6, m7', 348),
('Bati', 'P1, m3, P4, P5', 230),
('Bebop Dominant', 'P1, M2, M3, P4, P5, M6, m7, M7', 840),
('Bebop Half-Diminished', 'P1, m2, m3, P4, d5, P5, m6, M7', 840),
('Bebop Major I', 'P1, M2, M3, P4, P5, A5, M6, M7', 840),
('Bebop Major II', 'P1, M2, M3, P4, P5, m6, M6, M7', 840),
('Bebop Minor', 'P1, M2, m3, M3, P4, P5, M6, m7', 840),
('Bhairavi Thaat', 'P1, m2, m3, P4, P5, m6, m7', 356), -- North Indian
('Bhairav Thaat', 'P1, m2, M3, P4, P5, m6, M7', 356), -- North Indian
('Bhairubahar Thaat', 'P1, m2, M3, P4, P5, M6, M7', 356), -- North Indian
('Bilawal Thaat', 'P1, M2, m3, P4, P5, M6, M7', 356), -- North Indian
('Bi Yu', 'P1, m3, P5, m7', 156),
('"Black Key" Pentatonic', 'P1, M2, P4, P5, M6', NULL),
('Blues', 'P1, m3, P4, A4, P5, m7', 2),
('Blues Nine-Note', 'P1, M2, m3, M3, P4, d5, P5, M6, m7', 2),
('Blues Enneatonic', 'P1, M2, m3, M3, P4, d5, P5, M6, m7', 2),
('Blues Heptatonic', 'P1, m3, M3, P4, d5, P5, m7', 2),
('Blues Hexatonic', 'P1, m3, P4, d5, P5, m7', 2),
('Blues Pentatonic', 'P1, m3, P4, d5, m7', 2),
('Blues I', 'P1, m3, P4, d5, m7', 2),
('Blues Major ascending', 'P1, M2, A2, M3, P5, M6', 2),
('Blues Major descending', 'P8, M6, P5, M3, m3, M2', 2),
('Blues Minor ascending', 'P1, m3, P4, d5, P5, m7', 2),
('Blues Minor descending', 'P8, m7, P5, d5, P4, m3', 2),
('Blues Nonatonic', 'P1, M2, m3, M3, P4, d5, P5, M6, m7', 2),
('Blues III', 'P1, M2, m3, M3, P4, d5, P5, M6, m7', 2),
('Blues IV', 'P1, M2, m3, P4, d5, P5, M6, m7', 2),
('Blues V', 'P1, m3, M3, P4, d5, P5, m7, M7', 2),
('Blues VI', 'P1, m3, M3, P4, d5, P5, M6, m7, M7', 2),
('Bluesy R and R', 'P1, m3, M3, P4, P5, M6, m7', 2),
('Byzantine Liturgical Chromatic', 'P1, m2, M3, P4, P5, m6, M7', 792), -- Byzantine
('Byzantine', 'P1, m2, M3, P4, P5, m6, M7', 792), -- Byzantine
('Chad Gadyo', 'P1, M2, m3, P4, P5', 376), -- Jewish
('Chiao', 'P1, M2, m3, P4, P5, m6, m7', 156),
('Chaio Two', 'P1, M2, P4, m6, m7', 156),
('Chin', 'P1, m3, d5, m6, m7', 156),
('Chinese', 'P1, M3, A4, P5, M7', 156),
('Ching', 'P1, M3, A4, P5, M7', 156),
('Chromatic (Harmonic)', 'P1, m2, M2, m3, M3, P4, A4, P5, m6, M6, m7, M7', NULL),
('Chromatic (Melodic) ascending', 'P1, A1, M2, A2, M3, P4, A4, P5, A5, M6, A6, M7', NULL),
('Chromatic (Melodic) descending', 'P8, M7, m7, M6, m6, P5, d5, P4, M3, m3, M2, m2', NULL),
('Chromatic and Diatonic Dorian mixed', 'P1, m2, M2, m3, P4, P5, m6, M6, m7', NULL),
('Chromatic and Permuted Diatonic Dorian mixed', 'P1, m2, M2, M3, P4, P5, m6, M6, M7', NULL),
('Chromatic Dorian', 'P1, m2, M2, P4, P5, m6, M6', NULL),
('Chromatic Hypodorian', 'P1, M2, m3, M3, P5, m6, M6', NULL),
('Chromatic Hypodorian Inverse', 'P1, m3, M3, P4, m6, M6, m7', NULL),
('Chromatic Hypolydian', 'P1, m2, M3, d5, P5, m6, M7', NULL),
('Chromatic Hypolydian Inverse', 'P1, m2, M3, P4, d5, m6, M7', NULL),
('Chromatic Hypophrygian', 'P1, m3, P4, d5, P5, m7, M7', NULL),
('Chromatic Hypophrygian Inverse', 'P1, m2, M2, P4, d5, P5, M6', NULL),
('Chromatic Lydian', 'P1, m2, M3, P4, d5, M6, M7', NULL),
('Chromatic Lydian Inverse', 'P1, m2, m3, d5, P5, m6, M7', NULL),
('Chromatic Mixolydian', 'P1, m2, M2, P4, d5, P5, m7', NULL),
('Chromatic Mixolydian Inverse', 'P1, M2, P4, d5, P5, m7, M7', NULL),
('Chromatic Phrygian', 'P1, m3, M3, P4, m6, m7, M7', NULL),
('Chromatic Phrygian Inverse', 'P1, m2, M2, M3, P5, m6, M6', NULL),
('Cushak', 'P1, M2, m3, P4, P5, m6, m7', 51),
('Dastgah Mahur', 'P1, M2, M3, P4, P5, M6, M7', 364),
('Dastgah Rast Panjgah', 'P1, M2, M3, P4, P5, M6, M7', 364),
('Deuterus authenticus', 'P1, m2, m3, P4, P5, m6, m7', NULL),
('Deuterus plagis', 'P1, m2, m3, P4, d5, m6, m7', NULL),
('Diatonic Major', 'P1, M2, M3, P4, P5, M6, M7', NULL),
('Diatonic Minor', 'P1, M2, m3, P4, P5, m6, m7', NULL),
('Diminished', 'P1, M2, m3, P4, d5, m6, M6, M7', NULL),
('Diminished Blues', 'P1, m2, m3, M3, A4, P5, M6, m7', NULL),
('Diminished b9', 'P1, m2, m3, P4, d5, m6, d7', NULL),
('Diminished Locrian', 'P1, m2, m3, d4, d5, m6, d7', NULL),
('Diminished Whole-tone I', 'P1, m2, m3, d4, d5, m6, m7', NULL),
('Diminished Whole-tone II', 'P1, m2, A2, M3, A4, A5, m7', NULL),
('Dominant 7th', 'P1, M2, P4, P5, M6, m7', NULL),
('Dominant Bebop', 'P1, M2, M3, P4, P5, M6, m7, M7', NULL),
('Dominant Diminished', 'P1, A1, A2, M3, A4, P5, M6, m7', NULL),
('Dorian/Aeolian mixed', 'P1, M2, m3, P4, P5, m6, M6, m7', NULL),
('Dorian Bebop', 'P1, M2, m3, M3, P4, P5, M6, m7', 840),
('Dorian b2', 'P1, m2, m3, P4, P5, M6, m7', NULL),
('Dorian b2 b5', 'P1, m2, m3, P4, d5, M6, m7', NULL),
('Dorian b5', 'P1, M2, m3, P4, d5, M6, m7', NULL),
('Dorian b9', 'P1, m2, m3, P4, d5, M6, m7', NULL),
('Dorian Greek', 'P1, m2, m3, P4, P5, m6, m7', 300),
('Dorian', 'P1, M2, m3, P4, P5, M6, m7', NULL),
('Dorian Minor', 'P1, M2, m3, P4, P5, M6, m7', NULL),
('Dorian Pentatonic', 'P1, M2, P4, P5, m7', NULL),
('Dorian #4', 'P1, M2, m3, A4, P5, M6, m7', NULL),
('Dorian #11', 'P1, M2, m3, A4, P5, M6, m7', NULL),
('Dorico Flamenco', 'P1, m2, M3, P4, P5, m6, m7', 724),
('Double Harmonic Major', 'P1, m2, M3, P4, P5, m6, M7', NULL),
('Double Harmonic Minor', 'P1, M2, m3, A4, P5, m6, M7', NULL),
('Double-Phrygian Hexatonic', 'P1, m2, m3, P4, d5, M6', NULL),
('Egyptian', 'P1, M2, P4, P5, m7', 818),
('Eight-Note Chinese', 'P1, M2, M3, P4, P5, M6, m7, M7', 156),
('Eight-Note Diminished', 'P1, M2, m3, P4, d5, m6, M6, M7', NULL),
('Eight-Note Spanish', 'P1, m2, A2, M3, P4, d5, m6, m7', 724),
('Enigmatic', 'P1, m2, M3, A4, A5, A6, M7', 380),
('Ethiopian', 'P1, M2, M3, P4, P5, m6, M7', 230),
('Eskimo Heptatonic', 'P1, M2, m3, P4, P5, M6, m7', 840), -- Alaskan
('Eskimo Hexatonic 1', 'P1, M2, m3, P4, P5, m7', 840), -- Alaskan
('Eskimo Hexatonic 2 (Alaska : Point Hope)', 'P1, M2, M3, d5, m6, M7', 840), -- Alaskan
('Eskimo tetratonic (Alaska : Bethel)', 'P1, M2, M3, P5', 840), -- Alaskan
('Esplá''s', 'P1, m2, m3, M3, P4, d5, m6, m7', NULL),
('Ezel', 'P1, M2, m3, P4, P5, m6, m7', 230),
('Fifth Mode', 'P1, M2, M3, P4, P5, A6', NULL),
('Five-Note Prometheus', 'P1, M2, M3, d5, m7', NULL),
('Four Semitone I', 'P1, M3, m6', NULL),
('Four Semitone II', 'P1, M3, A5', NULL),
('Freygish', 'P1, m2, A2, M3, P4, d5, m6, m7', NULL),
('Full Minor', 'P1, M2, m3, P4, P5, m6, M6, m7, M7', NULL),
('Geez', 'P1, M2, m3, P4, P5, m6, m7', 230),
('Genus chromaticum', 'P1, m2, m3, M3, P4, P5, m6, M6, M7', NULL), -- Latin
('Genus diatonicum', 'P1, M2, M3, P4, P5, M6, m7, M7', 39), -- Latin
('Genus diatonicum veterum correctum', 'P1, M2, M3, P4, d5, P5, M6, M7', 39), -- Latin
('Genus primum', 'P1, M2, P4, P5', 39), -- Latin
('Genus secundum', 'P1, M3, P4, P5, M6, M7', 39), -- Latin
('Genus tertium', 'P1, m3, M3, P5, m6, M7', 39), -- Latin
('Ghana Heptatonic', 'P1, M2, M3, P4, P5, M6, M7', 2),
('Ghana Pentatonic I', 'P1, M2, m3, P4, P5', 2),
('Ghana Pentatonic II', 'P1, M2, M3, P5, M6', 2),
('Gnossiennes (Satie)', 'P1, M2, m3, A4, P5, M6, m7', 250),
('Gong', 'P1, M2, M3, P5, M6', 156),
('Gregorian no.1', 'P1, M2, m3, P4, P5, m6, M6, m7', 39), -- Church
('Gregorian no.2', 'P1, M2, M3, P4, P5, M6, m7', 39), -- Church
('Gregorian no.3', 'P1, m2, m3, P4, P5, m6, m7, M7', 39), -- Church
('Gregorian no.4', 'P1, M2, m3, P4, P5, m6, m7, M7', 39), -- Church
('Gregorian no.5', 'P1, M2, M3, P4, A4, P5, M6, M7', 39), -- Church
('Gregorian no.6', 'P1, M2, M3, P4, P5, M6, m7, M7', 39), -- Church
('Gregorian no.7', 'P1, M2, M3, P4, P5, M6, m7', 39), -- Church
('Gregorian no.8', 'P1, M2, m3, P4, P5, M6, m7', 39), -- Church
('Gypsy Hexatonic', 'P1, m2, M3, P4, P5, m6, M6', 642),
('Gypsy I', 'P1, M2, m3, A4, P5, m6, M7', 642),
('Gypsy II', 'P1, m2, M3, P4, P5, m6, M7', 642),
('Gu', 'P1, M2, M3, A4, P5, M6, M7', 156),
('Gu Xian', 'P1, m3, P4, P5, m7', 156),
('Half-Diminished', 'P1, M2, m3, P4, d5, m6, m7', NULL),
('Half-Diminished Bebop', 'P1, m2, m3, P4, d5, P5, m6, M7', 840),
('"Half-Whole step"', 'P1, m2, m3, M3, A4, P5, M6, m7', NULL),
('"Half-Whole tone"', 'P1, m2, m3, M3, A4, P5, M6, m7', NULL),
('Han-Iwato', 'P1, m2, P4, P5, m7', 392),
('Han-kumoi', 'P1, M2, P4, P5, m6', 392),
('"Hard" Pentatonic', 'P1, M2, P4, P5, M6', NULL),
('Harmonic and Neapolitan Minor mixed', 'P1, m2, M2, m3, P4, P5, m6, M7', NULL),
('Harmonic Major', 'P1, M2, M3, P4, P5, m6, M7', NULL),
('Harmonic Minor', 'P1, M2, m3, P4, P5, m6, M7', NULL),
('Hawaiian', 'P1, M2, m3, P4, P5, M6, M7', 840), -- Hawaiian
('Hawaiian 2', 'P1, M2, m3, P5, M6, M7', 840), -- Hawaiian
('Heptonia seconda', 'P1, M2, m3, P4, P5, M6, M7', NULL),
('Hindu', 'P1, M2, M3, P4, P5, m6, m7', 356),
('Hira-joshi', 'P1, M2, m3, P5, m6', 392),
('Hijaz', 'P1, M2, A3, A4, A5, M6, M7', 51),
('Hijaz Major', 'P1, m2, P4, d5, m6, M6, m7', 300),
('Hijazskiar Major', 'P1, m2, M3, P4, P5, m6, M7', 300),
('Honchoshi', 'P1, m2, P4, P5, m6, m7', 392),
('Honchoshi plagal form', 'P1, m2, m3, P4, d5, m7', 392),
('Honchoshi 2', 'P1, P4', 392),
('Hon-kumoi-joshi', 'P1, m2, P4, P5, m6', 392),
('Houseini', 'P1, M2, m3, M3, P4, P5, m6, M6, m7', 300),
('Houzam I Major', 'P1, A2, M3, P4, P5, M6, M7', 300),
('Houzam II', 'P1, m2, M3, d5, P5, M6, M7', 300),
('Hungarian Major', 'P1, A2, M3, A4, P5, M6, m7', 348),
('Hungarian Minor', 'P1, M2, m3, A4, P5, m6, M7', 348),
('Hyojo', 'P1, M2, m3, P4, P5, M6, m7', 392),
('Hyperaeolian', 'P1, m2, m3, P4, d5, m6, m7', 300),
('Hyperdorian', 'P1, m2, m3, P4, d5, m6, m7', 300),
('Hyperphrygian', 'P1, M2, m3, P4, P5, m6, m7', 300),
('Hypoaeolian', 'P1, m2, m3, P4, d5, m6, m7', 300),
('Hypodorian', 'P1, M2, m3, P4, P5, m6, m7', 300),
('Hypoionian', 'P1, M2, M3, P4, P5, M6, m7', 300),
('Hypolocrian', 'P1, M2, M3, A4, P5, M6, M7', 300),
('Hypolydian', 'P1, M2, M3, A4, P5, M6, M7', 300),
('Hypomixolydian', 'P1, M2, m3, P4, P5, M6, m7', 300),
('Hypophrygian', 'P1, M2, M3, P4, P5, M6, m7', 300),
('In', 'P1, m2, M2, P4, P5, m6, M6', 392),
('Insen', 'P1, m2, P4, P5, m6, m7', 392),
('Insen 2', 'P1, m2, P4, P5, m7', 392),
('Inverted Augmented', 'P1, m2, M3, P4, m6, M6', NULL),
('Ionian Augmented', 'P1, M2, M3, P4, A5, M6, M7', NULL),
('Ionian Iastian', 'P1, M2, M3, P4, P5, M6, m7', 300),
('Ionian', 'P1, M2, M3, P4, P5, M6, M7', NULL),
('Ionian Pentatonic', 'P1, M2, M3, P5, M6', NULL),
('Ionian #5', 'P1, M2, M3, P4, A5, M6, M7', NULL),
('Ichikotsucho', 'P1, M2, M3, P4, A4, P5, M6, M7', 392),
('Iwato', 'P1, m2, P4, d5, m7', 392),
('John Foulds'' ''Mantra of Will''', 'P1, m2, M3, d5, P5, m6, M6', NULL),
('Japanese mode', 'P1, M2, m3, P5, m6', 392),
('Jazz Minor', 'P1, M2, m3, P4, P5, M6, M7', 2),
('Jazz Minor inverse', 'P1, m2, m3, P4, P5, M6, m7', NULL),
('Jeths'' mode', 'P1, M2, m3, P4, d5, M6, M7', NULL),
('Jewish I', 'P1, m2, A2, M3, P4, d5, m6, m7', 376), -- Jewish
('Jewish II', 'P1, m2, M3, P4, P5, m6, m7', 376), -- Jewish
('Jiao', 'P1, m3, P4, m6, m7', 156),
('Jia Zhong', 'P1, m3, P4, P5, m7', 156),
('Jin Yu', 'P1, M2, P4, P5, m7', 156),
('Kaffa', 'P1, M2, m3, d5, P5, M6, m7', 230),
('Kafi Thaat', 'P1, M2, m3, P4, P5, M6, m7', 356), -- North Indian
('Kalyan Thaat (Yaman)', 'P1, M2, M3, A4, P5, M6, M7', 356), -- North Indian
('Kartzihiar', 'P1, M2, m3, P4, d5, M6, m7', 300),
('Kata-kumoi', 'P1, M2, m3, P5, m6', 392),
('Khammaj Thaat', 'P1, M2, M3, P4, P5, M6, m7', 356), -- North Indian
('Kiourdi Minor', 'P1, M2, m3, P4, d5, P5, m6, M6, m7', 300),
('Kiourdi ascending', 'P1, M2, m3, P4, d5, M6, m7', 300),
('Kiourdi descending', 'P8, m7, m6, P5, P4, m3, M2', 300),
('Kokin-joshi', 'P1, m2, P4, P5, m7', 392),
('Kubilai''s Mongol', 'P1, M2, M3, P4, d5, P5, M6, M7', 496),
('Kung', 'P1, M2, M3, A4, P5, M6, M7', 156),
('Kung 2', 'P1, M2, M3, A4, M6', 156),
('Kyemyonjo', 'P1, m3, P4, P5, M6', 410),
('Leading Whole-tone', 'P1, M2, M3, A4, A5, A6, M7', NULL),
('Locrian Diminished', 'P1, m2, m3, d4, d5, m6, d7', NULL),
('Locrian b4', 'P1, m2, m3, d4, d5, m6, m7', NULL),
('Locrian b7', 'P1, m2, m3, P4, d5, m6, d7', NULL),
('Locrian bb7', 'P1, m2, m3, P4, d5, m6, d7', NULL),
('Locrian', 'P1, m2, m3, P4, d5, m6, m7', NULL),
('Locrian Nat. 2', 'P1, M2, m3, P4, d5, m6, m7', NULL),
('Locrian Nat. 2/6', 'P1, M2, m3, P4, d5, M6, m7', NULL),
('Locrian Nat. 2 b7', 'P1, M2, m3, P4, d5, m6, d7', NULL),
('Locrian Nat. 6', 'P1, m2, m3, P4, d5, M6, m7', NULL),
('Locrian #2 bb7', 'P1, M2, m3, P4, d5, m6, d7', NULL),
('Locrian #2', 'P1, M2, m3, P4, d5, m6, m7', NULL),
('Locrian #6', 'P1, m2, m3, P4, d5, M6, m7', NULL),
('Locrian #2 #6', 'P1, M2, m3, P4, d5, M6, m7', NULL),
('Lydian Augmented', 'P1, M2, M3, A4, A5, M6, M7', NULL),
('Lydian Augmented #2', 'P1, A2, M3, A4, A5, M6, M7', NULL),
('Lydian Diminished', 'P1, M2, m3, A4, P5, M6, M7', NULL),
('Lydian Dominant', 'P1, M2, M3, A4, P5, M6, m7', NULL),
('Lydian b3', 'P1, M2, m3, A4, P5, M6, M7', NULL),
('Lydian b7', 'P1, M2, M3, A4, P5, M6, m7', NULL),
('Lydian (Greek)', 'P1, M2, M3, P4, P5, M6, M7', 300),
('Lydian Hexatonic', 'P1, M2, P4, P5, M6, M7', NULL),
('Lydian', 'P1, M2, M3, A4, P5, M6, M7', NULL),
('Lydian Minor b7', 'P1, M2, m3, A4, P5, M6, m7', NULL),
('Lydian-Mixolydian', 'P1, M2, M3, A4, P5, M6, m7', NULL),
('Lydian/Mixolydian mixed', 'P1, M2, M3, P4, d5, P5, M6, m7, M7', NULL),
('Lydian #2', 'P1, A2, M3, A4, P5, M6, M7', NULL),
('Lydian #5', 'P1, M2, M3, A4, A5, M6, M7', NULL),
('Lydian #9', 'P1, A2, M3, A4, P5, M6, M7', NULL),
('Magen Abot', 'P1, m2, A2, M3, d5, m6, A6, M7', 376), -- Jewish
('Major', 'P1, M2, M3, P4, P5, M6, M7', NULL),
('Major Augmented', 'P1, M2, M3, P4, A5, M6, M7', NULL),
('Major Bebop I', 'P1, M2, M3, P4, P5, A5, M6, M7', 840),
('Major Bebop II', 'P1, M2, M3, P4, P5, m6, M6, M7', 840),
('Major Blues ascending', 'P1, M2, A2, M3, P5, M6', NULL),
('Major Blues descending', 'P8, M6, P5, M3, m3, M2', NULL),
('Major/Dorian mixed', 'P1, M2, m3, M3, P4, P5, M6, m7, M7', NULL),
('Major Gipsy', 'P1, m2, M3, P4, P5, m6, M7', 642),
('Major inverse', 'P1, m2, m3, P4, P5, m6, m7', NULL),
('Major Locrian mode', 'P1, M2, M3, P4, d5, m6, m7', NULL),
('Major/Lydian mixed', 'P1, M2, M3, P4, d5, P5, M6, M7', NULL),
('Major-Minor', 'P1, M2, M3, P4, P5, m6, m7', NULL),
('Major/Minor mixed', 'P1, M2, m3, M3, P4, P5, m6, M6, m7, M7', NULL),
('Major/Mixolydian mixed', 'P1, M2, M3, P4, P5, M6, m7, M7', NULL),
('Major Pentatonic', 'P1, M2, M3, P5, M6', NULL),
('Man Gong', 'P1, m3, P4, m6, m7', 156),
('Man Jue', 'P1, M2, M3, P5, M6', 156),
('Makam Bûselik I', 'P1, M2, m3, P4, P5, m6, m7', NULL),
('Makam Bûselik II', 'P1, M2, m3, P4, P5, m6, M7', 368),
('Makam Hijaz', 'P1, m2, M3, P4, P5, m6, m7, M7', 368),
('Makam Rast', 'P1, M2, M3, P4, P5, M6, m7, M7', 368),
('Maqam Ajam Ashiran', 'P1, M2, M3, P4, P5, M6, M7', 368),
('Maqam Bayat-e-Esfahan', 'P1, M2, m3, P4, P5, m6, M7', 368),
('Maqam Hedjaz', 'P1, M2, m3, d5, P5, M6, m7', 368),
('Maqam Hicaz', 'P1, m2, M3, P4, P5, M6, m7', 368),
('Maqam Hijaz Kar', 'P1, m2, M3, P4, P5, m6, M7', 368),
('Maqam Hisar', 'P1, M2, m3, d5, P5, m6, M7', 368),
('Maqam Humayun', 'P1, m2, M3, P4, P5, m6, m7', 368),
('Maqam Huzzam', 'P1, m2, m3, d4, P5, m6, m7', 368),
('Maqam Karcigar', 'P1, M2, m3, P4, d5, M6, m7', 368),
('Maqam Kurd', 'P1, m2, m3, P4, P5, m6, m7', 368),
('Maqam Lami', 'P1, m2, m3, P4, d5, m6, m7', 368),
('Maqam Nahawand', 'P1, P5, m6, m7', 368),
('Maqam Nahawand Murassah', 'P1, M2, m3, P4, d5, M6, m7', 368),
('Maqam Nakriz', 'P1, M2, m3, d5, P5, M6, m7', 368),
('Maqam Nawa Athar', 'P1, M2, m3, d5, P5, m6, M7', 368),
('Maqam Nihavend', 'P1, M2, m3, P4, P5, m6, m7', 368),
('Maqam Saba Zamzam', 'P1, m2, m3, d4, P5, m6, m7', 368),
('Maqam Shadd''araban', 'P1, m2, m3, M3, P4, d5, M6, m7', 368),
('Maqam Shahnaz Kurdi', 'P1, m2, m3, P4, P5, m6, M7', 368),
('Maqam Shawq Awir', 'P1, M2, M3, P4, P5, M6, m7, M7', 368),
('Maqam Sultani Yakah', 'P1, M2, m3, P4, P5, m6, M7', 368),
('Maqam Suzidil', 'P1, m2, M3, P4, P5, m6, M7', 368),
('Maqam Tarznauyn', 'P1, m2, m3, P4, d5, M6, m7', 368),
('Maqam Ussâk', 'P1, M2, M3, P4, P5, m6, m7', 368),
('Maqam Zanjaran', 'P1, m2, M3, P4, P5, M6, m7', 368),
('Maqam Zengule', 'P1, m2, M3, P4, P5, m6, m7', 368),
('Marwa Thaat', 'P1, m2, M3, d5, P5, M6, M7', 356), -- North Indian
('Mela Bhavapriya', 'P1, m2, m3, d5, P5, m6, m7', 356), -- South Indian
('Mela Cakravaka', 'P1, m2, M3, P4, P5, M6, m7', 356), -- South Indian
('Mela Calanata', 'P1, m3, M3, P4, P5, m7', 356), -- South Indian
('Mela Carukesi', 'P1, M2, M3, P4, P5, m6, m7', 356), -- South Indian
('Mela Citrambari', 'P1, M2, M3, d5, P5, m7, M7', 356), -- South Indian
('Mela Dharmavati', 'P1, M2, m3, d5, P5, M6, M7', 356), -- South Indian
('Mela Dhatuvardhani', 'P1, m3, M3, d5, P5, m6, M7', 356), -- South Indian
('Mela Dhavalambari', 'P1, m2, M3, d5, P5, m6, M6', 356), -- South Indian
('Mela Dhenuka', 'P1, m2, m3, P4, P5, m6, M7', 356), -- South Indian
('Mela Divyamani', 'P1, m2, m3, d5, P5, m7, M7', 356), -- South Indian
('Mela Gamanasrama', 'P1, m2, M3, d5, P5, M6, M7', 356), -- South Indian
('Mela Ganamurti', 'P1, m2, M2, P4, P5, m6, M7', 356), -- South Indian
('Mela Gangeyabhusani', 'P1, m3, M3, P4, P5, m6, M7', 356), -- South Indian
('Mela Gaurimanohari', 'P1, M2, m3, P4, P5, M6, M7', 356), -- South Indian
('Mela Gavambodhi', 'P1, m2, m3, d5, P5, m6, M6', 356), -- South Indian
('Mela Gayakapriya', 'P1, m2, M3, P4, P5, m6, M6', 356), -- South Indian
('Mela Harikambhoji', 'P1, M2, M3, P4, P5, M6, m7', 356), -- South Indian
('Mela Hatakambari', 'P1, m2, M3, P4, P5, m7, M7', 356), -- South Indian
('Mela Hemavati', 'P1, M2, m3, d5, P5, M6, m7', 356), -- South Indian
('Mela Jalarnava', 'P1, m2, M2, d5, P5, m6, m7', 356), -- South Indian
('Mela Jhalavarali', 'P1, m2, M2, d5, P5, m6, M7', 356), -- South Indian
('Mela Jhankaradhvani', 'P1, M2, m3, P4, P5, m6, M6', 356), -- South Indian
('Mela Jyotisvarupini', 'P1, m3, M3, d5, P5, m6, m7', 356), -- South Indian
('Mela Kamavardhani', 'P1, m2, M3, d5, P5, m6, M7', 356), -- South Indian
('Mela Kanakangi', 'P1, m2, M2, P4, P5, m6, M6', 356), -- South Indian
('Mela Kantamani', 'P1, M2, M3, d5, P5, m6, M6', 356), -- South Indian
('Mela Kharaharapriya', 'P1, M2, m3, P4, P5, M6, m7', 356), -- South Indian
('Mela Kiravani', 'P1, M2, m3, P4, P5, m6, M7', 356), -- South Indian
('Mela Kokilapriya', 'P1, m2, m3, P4, P5, M6, M7', 356), -- South Indian
('Mela Kosalam', 'P1, m3, M3, d5, P5, M6, M7', 356), -- South Indian
('Mela Latangi', 'P1, M2, M3, d5, P5, m6, M7', 356), -- South Indian
('Mela Manavati', 'P1, m2, M2, P4, P5, M6, M7', 356), -- South Indian
('Mela Mararanjani', 'P1, M2, M3, P4, P5, m6, M6', 356), -- South Indian
('Mela Mayamalavagaula', 'P1, m2, M3, P4, P5, m6, M7', 356), -- South Indian
('Mela Mecakalyani', 'P1, M2, M3, d5, P5, M6, M7', 356), -- South Indian
('Mela Naganandini', 'P1, M2, M3, P4, P5, A6, M7', 356), -- South Indian
('Mela Namanarayani', 'P1, m2, M3, d5, P5, m6, m7', 356), -- South Indian
('Mela Nasikabhusani', 'P1, m3, M3, d5, P5, M6, m7', 356), -- South Indian
('Mela Natabhairavi', 'P1, M2, m3, P4, P5, m6, m7', 356), -- South Indian
('Mela Natakapriya', 'P1, m2, m3, P4, P5, M6, m7', 356), -- South Indian
('Mela Navanitam', 'P1, m2, M2, d5, P5, M6, m7', 356), -- South Indian
('Mela Nitimati', 'P1, M2, m3, d5, P5, m7, M7', 356), -- South Indian
('Mela Pavani', 'P1, m2, M2, d5, P5, M6, M7', 356), -- South Indian
('Mela Ragavardhani', 'P1, m3, M3, P4, P5, m6, m7', 356), -- South Indian
('Mela Raghupriya', 'P1, m2, M2, d5, P5, m7, M7', 356), -- South Indian
('Mela Ramapriya', 'P1, m2, M3, d5, P5, M6, m7', 356), -- South Indian
('Mela Rasikapriya', 'P1, m3, M3, d5, P5, m7, M7', 356), -- South Indian
('Mela Ratnangi', 'P1, m2, M2, P4, P5, m6, m7', 356), -- South Indian
('Mela Risabhapriya', 'P1, M2, M3, d5, P5, m6, m7', 356), -- South Indian
('Mela Rupavati', 'P1, m2, m3, P4, P5, m7, M7', 356), -- South Indian
('Mela Sadvidhamargini', 'P1, m2, m3, d5, P5, M6, m7', 356), -- South Indian
('Mela Salaga', 'P1, m2, M2, d5, P5, m6, M6', 356), -- South Indian
('Mela Sanmukhapriya', 'P1, M2, m3, d5, P5, m6, m7', 356), -- South Indian
('Mela Sarasangi', 'P1, M2, M3, P4, P5, m6, M7', 356), -- South Indian
('Mela Senavati', 'P1, m2, m3, P4, P5, m6, M6', 356), -- South Indian
('Mela Shankarabharanam', 'P1, M2, M3, P4, P5, M6, M7', 356), -- South Indian
('Mela Shubhapantuvarali', 'P1, m2, m3, d5, P5, m6, M7', 356), -- South Indian
('Mela Simhendramadhyama', 'P1, M2, m3, d5, P5, m6, M7', 356), -- South Indian
('Mela Sucaritra', 'P1, m3, M3, d5, P5, m6, M6', 356), -- South Indian
('Mela Sulini', 'P1, m3, M3, P4, P5, M6, M7', 356), -- South Indian
('Mela Suryakanta', 'P1, m2, M3, P4, P5, M6, M7', 356), -- South Indian
('Mela Suvarnangi', 'P1, m2, m3, d5, P5, M6, M7', 356), -- South Indian
('Mela Tanarupi', 'P1, m2, M2, P4, P5, m7, M7', 356), -- South Indian
('Mela Vacaspati', 'P1, M2, M3, d5, P5, M6, m7', 356), -- South Indian
('Mela Vagadhisvari', 'P1, m3, M3, P4, P5, M6, m7', 356), -- South Indian
('Mela Vakulabharanam', 'P1, m2, M3, P4, P5, m6, m7', 356), -- South Indian
('Mela Vanaspati', 'P1, m2, M2, P4, P5, M6, m7', 356), -- South Indian
('Mela Varunapriya', 'P1, M2, m3, P4, P5, m7, M7', 356), -- South Indian
('Mela Visvambhari', 'P1, m2, M3, d5, P5, m7, M7', 356), -- South Indian
('Mela Yagapriya', 'P1, m3, M3, P4, P5, m6, M6', 356), -- South Indian
('Melodic Major', 'P1, M2, M3, P4, P5, m6, m7', NULL),
('Melodic Minor ascending', 'P1, M2, m3, P4, P5, M6, M7', NULL),
('Melodic Minor descending', 'P8, m7, m6, P5, P4, m3, M2', NULL),
('Melodic Minor #11', 'P1, M2, m3, A4, P5, M6, M7', NULL),
('Messiaen mode 1', 'P1, M2, M3, A4, A5, A6', NULL),
('Messiaen mode 2', 'P1, m2, m3, M3, A4, P5, M6, m7', NULL),
('Messiaen mode 2 inverse', 'P1, M2, m3, P4, d5, m6, M6, M7', NULL),
('Messiaen mode 3', 'P1, M2, m3, M3, A4, P5, m6, m7, M7', NULL),
('Messiaen mode 4', 'P1, m2, M2, P4, A4, P5, m6, M7', NULL),
('Messiaen mode 4 inverse', 'P1, m3, M3, P4, d5, M6, m7, M7', NULL),
('Messiaen mode 5', 'P1, m2, M2, P4, A4, P5, m6, M7', NULL),
('Messiaen mode 6', 'P1, M2, M3, P4, A4, A5, A6, M7', NULL),
('Messiaen mode 6 inverse', 'P1, M2, M3, P4, d5, m6, m7, M7', NULL),
('Messiaen mode 7', 'P1, m2, M2, m3, P4, A4, P5, m6, M6, M7', NULL),
('Messiaen mode 7 inverse', 'P1, M2, m3, M3, P4, d5, m6, M6, m7, M7', NULL),
('Messiaen truncated mode 5', 'P1, m2, A4, P5', NULL),
('Messiaen truncated mode 5 inverse', 'P1, P4, A4, M7', NULL),
('Messiaen truncated mode 6', 'P1, M2, A4, A5', NULL),
('Messiaen truncated mode 6 inverse', 'P1, M3, A4, A6', NULL),
('Minor Bebop', 'P1, M2, m3, M3, P4, P5, M6, m7', NULL),
('Minor Blues ascending', 'P1, m3, P4, d5, P5, m7', NULL),
('Minor Blues descending', 'P8, m7, P5, d5, P4, m3', NULL),
('Minor b5', 'P1, M2, m3, P4, d5, m6, m7', NULL),
('Minor Gipsy', 'P1, M2, m3, A4, P5, m6, M7', 642),
('Minor Gipsy inverse', 'P1, m2, M3, P4, d5, M6, m7', 642),
('Minor Hexatonic', 'P1, M2, m3, P4, P5, m7', NULL),
('Minor Locrian', 'P1, M2, m3, P4, d5, m6, m7', NULL),
('Minor-Major', 'P1, M2, m3, P4, P5, M6, M7', NULL),
('Minor Pentatonic', 'P1, m3, P4, P5, m7', NULL),
('Minor Pentatonic with leading notes', 'P1, M2, m3, M3, P4, d5, P5, M6, m7, M7', NULL),
('Minyo', 'P1, m3, P4, m6, m7', 392),
('Mischung 1', 'P1, M2, m3, P4, P5, M6, M7', 276),
('Mischung 2', 'P1, M2, M3, P4, P5, m6, M7', 276),
('Mischung 3 Mixolydian', 'P1, M2, M3, P4, P5, M6, m7', 276),
('Mischung 4', 'P1, M2, m3, P4, P5, m6, M7', 276),
('Mischung 5 Dorian', 'P1, M2, m3, P4, P5, M6, m7', 276),
('Mischung 6', 'P1, M2, M3, P4, P5, m6, m7', 276),
('Misheberekh', 'P1, M2, m3, d5, P5, M6, m7', 376), -- Jewish
('Mixolydian Bebop', 'P1, M2, M3, P4, P5, M6, m7, M7', NULL),
('Mixolydian/Dorian mixed', 'P1, M2, m3, M3, P4, P5, M6, m7', NULL),
('Mixolydian b2', 'P1, m2, M3, P4, P5, M6, m7', NULL),
('Mixolydian b6', 'P1, M2, M3, P4, P5, m6, m7', NULL),
('Mixolydian b6 b9', 'P1, m2, M3, P4, P5, m6, m7', NULL),
('Mixolydian b9', 'P1, m2, M3, P4, P5, M6, m7', NULL),
('Mixolydian b9 b13', 'P1, m2, M3, P4, P5, m6, m7', NULL),
('Mixolydian Greek', 'P1, m2, m3, P4, d5, m6, m7', 300),
('Mixolydian Hexatonic', 'P1, M2, P4, P5, M6, m7', NULL),
('Mixolydian', 'P1, M2, M3, P4, P5, M6, m7', NULL),
('Mixolydian Pentatonic', 'P1, M2, P4, P5, M6', NULL),
('Mixolydian #1', 'P1, m2, m3, d4, d5, m6, d7', NULL),
('Mixolydian #4', 'P1, M2, M3, A4, P5, M6, m7', NULL),
('Miyako-bushi I', 'P1, m2, M2, P4, P5, m6, M6', 392),
('Miyako-bushi II', 'P1, m2, P4, P5, m7', 392),
('Modes of Major Pentatonic mixed', 'P1, M2, m3, M3, P4, P5, m6, M6, m7', NULL),
('Modified Blues', 'P1, M2, m3, P4, d5, P5, m7', NULL),
('Modus conjunctus', 'P1, M2, m3, P4, d5, m6, M6, M7', NULL),
('Moorish Phrygian', 'P1, m2, m3, M3, P4, P5, m6, m7, M7', 724),
('Nando-kyemyonjo', 'P1, M2, m3, P4, P5', 410),
('Natural Minor', 'P1, M2, m3, P4, P5, m6, m7', NULL),
('Neapolitan Major', 'P1, m2, m3, P4, P5, M6, M7', 380),
('Neapolitan Minor', 'P1, m2, m3, P4, P5, m6, M7', 380),
('Neveseri', 'P1, m2, m3, d5, P5, m6, m7, M7', 300),
('Niagari', 'P1, m2, P4, P5, m6, m7', 392),
('Niagari 2', 'P1, P5', 392),
('Niaventi Minor', 'P1, M2, m3, d5, P5, m6, M7', 300),
('Nihavend', 'P1, M2, m3, P4, P5, m6, M7', 51),
('Nigriz', 'P1, M2, m3, d5, P5, M6, m7', 300),
('Nine-Note Blues', 'P1, M2, m3, M3, P4, d5, P5, M6, m7', NULL),
('Nine-Note', 'P1, M2, m3, M3, d5, P5, m6, M6, M7', NULL),
('Nohkan Flute', 'P1, M2, P4, d5, m6, M6, M7', 392),
('Octatonic (Half-tone, Whole-tone', 'P1, m2, m3, M3, A4, P5, M6, m7', NULL),
('Octatonic (Whole-tone, Half-tone)', 'P1, M2, m3, P4, A4, A5, M6, M7', NULL),
('Olympos Enharmonic', 'P1, m2, P4, P5, m6', 300),
('Oriental', 'P1, m2, M3, P4, d5, M6, m7', 156),
('Oshikicho : Japan', 'P1, M2, m3, P4, P5, M6, m7', 392),
('Ousak Minor', 'P1, m2, m3, P4, P5, m6, m7', 300),
('Overtone', 'P1, M2, M3, A4, P5, M6, m7', NULL),
('Peiraiotikos', 'P1, m2, M3, d5, P5, M6, M7', 300),
('Peiraiotikos Minor', 'P1, M2, m3, d5, P5, M6, m7', 300),
('Pelog', 'P1, m2, m3, P5, m7', NULL),
('Pentatonic Blues', 'P1, m3, P4, d5, m7', NULL),
('Pentatonic Major', 'P1, M2, M3, P5, M6', NULL),
('Pentatonic Major b2', 'P1, m2, M3, P5, M6', NULL),
('Pentatonic Major b2 b5', 'P1, m2, M3, d5, M6', NULL),
('Pentatonic Major b2 b6', 'P1, m2, M3, P5, m6', NULL),
('Pentatonic Major b3', 'P1, m2, m3, d5, M6', NULL),
('Pentatonic Major b3 b6', 'P1, M2, m3, P5, m6', NULL),
('Pentatonic Major b5', 'P1, M2, M3, d5, M6', NULL),
('Pentatonic Major b6', 'P1, M2, M3, P5, m6', NULL),
('Pentatonic Major b9', 'P1, m2, M3, P5, M6', NULL),
('Pentatonic Major #9 b7', 'P1, A2, M3, P5, m7', NULL),
('Pentatonic Minor', 'P1, m3, P4, P5, m7', NULL),
('Pentatonic Minor added 6', 'P1, m3, P4, P5, M6, m7', NULL),
('Pentatonic Minor Major 6', 'P1, m3, P4, P5, M6', NULL),
('Pentatonic Minor 7 b5', 'P1, m3, P4, d5, m7', NULL),
('Pentatonic Whole Tone', 'P1, M3, A4, A5, A6', NULL),
('Persian', 'P1, m2, M3, P4, P5, m6, M7', 364), -- Persian
('Peruvian Major', 'P1, M2, M3, P4, P5, M6, M7', 604),
('Peruvian Minor', 'P1, M2, m3, P4, P5, m6, m7', 604),
('Peruvian Pentatonic 1', 'P1, M2, M3, P5, M6', 604),
('Peruvian Pentatonic 2', 'P1, m3, P4, P5, m7', 604),
('Peruvian tritonic 1', 'P1, M3, P5', 604),
('Peruvian tritonic 2', 'P1, m3, M6', 604),
('Petrushka chord', 'P1, A1, M3, A4, P5, M6, A6', 643),
('Phrygian/Aeolian mixed', 'P1, m2, M2, m3, P4, P5, m6, m7', NULL),
('Phrygian Dominant', 'P1, m2, M3, P4, P5, m6, m7', NULL),
('Phrygian b4', 'P1, m2, m3, d4, P5, m6, m7', NULL),
('Phrygian Greek', 'P1, M2, m3, P4, P5, M6, m7', 300),
('Phrygian Hexatonic', 'P1, m3, P4, P5, m6, m7', NULL),
('Phrygian/Locrian mixed', 'P1, m2, m3, P4, d5, P5, m6, m7', NULL),
('Phrygian Major', 'P1, m2, M3, P4, P5, m6, m7', NULL),
('Phrygian', 'P1, m2, m3, P4, P5, m6, m7', NULL),
('Phrygian-Mixolydian', 'P1, m2, m3, P4, P5, M6, m7', NULL),
('Phrygian Pentatonic', 'P1, m3, P4, m6, m7', NULL),
('Phrygian Nat. 6', 'P1, m2, m3, P4, P5, M6, m7', NULL),
('Phrygian #6', 'P1, m2, m3, P4, P5, M6, m7', NULL),
('Pien chih', 'P1, m2, m3, P4, d5, m6, m7', 156),
('Pilu Thaat', 'P1, M2, m3, P4, P5, m6, M7', 356),
('Ping', 'P1, M2, M3, A4, P5, M6, M7', 156),
('Pireotikos', 'P1, m2, M3, d5, P5, m6, M7', 300),
('Pomeroy', 'P1, m2, m3, d4, d5, m6, m7', NULL),
('Primary Pentatonic', 'P1, M2, M3, P5, M6', NULL),
('Prokofiev', 'P1, m2, m3, P4, d5, m6, m7, M7', NULL),
('Prometheus', 'P1, M2, M3, d5, M6, m7', NULL),
('Prometheus Liszt', 'P1, m2, M3, P4, m6, M6', NULL),
('Prometheus Neapolitan', 'P1, m2, M3, d5, M6, m7', NULL),
('Protus authentus', 'P1, M2, m3, P4, P5, M6, m7', NULL),
('Protus plagis', 'P1, M2, m3, P4, P5, m6, m7', NULL),
('Pure Minor', 'P1, M2, m3, P4, P5, m6, m7', NULL),
('Purvi Thaat', 'P1, m2, M3, d5, P5, m6, m7', 356), -- North Indian
('P''yongjo', 'P1, M2, P4, P5, M6, m7', 410),
('P''yongjo 2', 'P1, M2, P4, P5, M6', 410),
('P''yongjo-kyemyonjo', 'P1, m3, P4, P5, m7', 410),
('Pyramid Hexatonic', 'P1, M2, m3, P4, d5, M6', NULL),
('Qing Shang', 'P1, m3, P4, P5, m7', 156),
('Qing Yu', 'P1, M2, P4, P5, m7', 156),
('Quan Ming', 'P1, m3, P4, m6, m7', 156),
('Raga Abheri', 'P1, m3, P4, P5, m7', 356),
('Raga Abhogi', 'P1, M2, m3, P4, M6', 356),
('Raga Adana', 'P1, M2, m3, P4, P5, m6, m7', 356),
('Raga Ahira-Lalita', 'P1, m2, M3, P4, d5, M6, m7', 356),
('Raga Ahir Bhairav', 'P1, m2, M3, P4, P5, M6, m7', 356),
('Raga Ahiri Todi', 'P1, m2, m3, P4, P5, M6, m7', 356),
('Raga Airavati', 'P1, M2, M3, A4, P5, M6', 356),
('Raga Alhaiya Bilaval', 'P1, M2, M3, P4, P5, M6, m7, M7', 356),
('Raga Amarasenapriya', 'P1, M2, m3, d5, P5, M7', 356),
('Raga Ambika', 'P1, M2, m3, d5, P5, M6, M7', 356),
('Raga Amritavarshini', 'P1, M3, A4, P5, M7', 356),
('Raga Anandabhairavi', 'P1, M2, m3, P4, P5, m6, M6, m7', 356),
('Raga Andolika', 'P1, M2, P4, P5, M6, m7', 356),
('Raga Antara Kaishiaki', 'P1, M3, P5, m7', 356),
('Raga Arabhi', 'P1, M2, P4, P5, M6', 356),
('Raga Arunajualita', 'P1, M2, m3, d5, P5, M6, M7', 356),
('Raga Asavari (Asaveri)', 'P1, m2, m3, P4, P5, m6, m7', 356),
('Raga Audav Tukhari', 'P1, M2, m3, P4, m6', 356),
('Raga Baduhari', 'P1, M3, P4, P5, M6, m7', 356),
('Raga Bageshri', 'P1, M2, m3, P4, P5, M6, m7', 356),
('Raga Bageshri 2', 'P1, M2, m3, P4, M6, m7', 356),
('Raga Bahar', 'P1, M2, m3, P4, P5, M6, m7, M7', 356),
('Raga Bairagi (Baira)', 'P1, m2, P4, P5, m7', 356),
('Raga Barbara', 'P1, M2, M3, d5, M6, m7', 356),
('Raga Basant', 'P1, m2, M3, d5, P5, m6, M7', 356),
('Raga Bauli', 'P1, m2, M3, P5, m6, M7', 356),
('Raga Bhankar', 'P1, m2, M3, P4, d5, M6, m6', 356),
('Raga Bhanumanjari', 'P1, m3, M3, P4, P5, m7', 356),
('Raga Bhanumati', 'P1, m2, M2, P4, P5, M6, m7', 356),
('Raga Bhatiyar', 'P1, m2, M3, P4, d5, P5, M6, M7', 356),
('Raga Bhavani', 'P1, m2, m3, d5, P5, m6, m7', 356),
('Raga Bhavani 2', 'P1, M2, P4, M6', 356),
('Raga Bhimpalasi', 'P1, M2, m3, P4, P5, M6, m7', 356),
('Raga Bhinna Pancama', 'P1, M2, P4, P5, m6, M7', 356),
('Raga Bhinna Shadja', 'P1, M3, P4, M6, M7', 356),
('Raga Bhogachayanata', 'P1, m3, M3, P4, P5, M6, m7', 356),
('Raga Bhup (Bhopali)', 'P1, M2, M3, P5, M6', 356),
('Raga Bhupalam', 'P1, m2, m3, P5, m6', 356),
('Raga Bhupala Todi', 'P1, m2, m3, P5, m6', 356),
('Raga Bhupeshwari', 'P1, M2, M3, P5, m6', 356),
('Raga Bhusavati', 'P1, M2, M3, d5, P5, M6, m7', 356),
('Raga Bibhas (bhairava)', 'P1, m2, m3, P5, m6', 356),
('Raga Bibhas (marva)', 'P1, m2, M3, P5, M6', 356),
('Raga Bihag', 'P1, M2, M3, P4, d5, P5, M6, M7', 356),
('Raga Bilahari', 'P1, M2, M3, P5, M6', 356),
('Raga Bilashkhani', 'P1, m2, m3, P4, P5, m6, m7', 356),
('Raga Bilwadala', 'P1, M3, M6', 356),
('Raga Bindumalini', 'P1, m2, M3, P4, P5, M6, m7', 356),
('Raga Brindabani Sarang', 'P1, M2, P4, P5, m7, M7', 356),
('Raga Brindabani Tilang', 'P1, M3, P4, P5, m7, M7', 356),
('Raga Budhamanohari', 'P1, M2, M3, P4, P5', 356),
('Raga Camara', 'P1, M2, m3, d5, P5, m6, m7', 356),
('Raga Chandrajyoti', 'P1, m2, M2, d5, P5, M6', 356),
('Raga Chandrakauns (kafi)', 'P1, m3, P4, M6, m7', 356),
('Raga Chandrakauns (kiravani)', 'P1, m3, P4, m6, M7', 356),
('Raga Chandrakauns (modern)', 'P1, m3, P4, M6, M7', 356),
('Raga Charukeshi', 'P1, M2, M3, P4, P5, m6, m7', 356),
('Raga Chaturangini', 'P1, M2, M3, A4, P5, m7, M7', 356),
('Raga Chaturangini 2', 'P1, M2, M3, A4, P5, M7', 356),
('Raga Chaya Nat', 'P1, M2, M3, P4, A4, P5, M6, M7', 356),
('Raga Chaya Vati', 'P1, m2, M3, P4, M6, M7', 356),
('Raga Chaya Todi', 'P1, m2, m3, d5, m6', 356),
('Raga Chinthamani', 'P1, M2, m3, d5, P5, m6, M6, m7', 356),
('Raga Chinthamani 2', 'P1, M2, m3, d5, P5, m6, m7', 356),
('Raga Chitthakarshini', 'P1, m2, m3, P4, m6', 356),
('Raga Cudamani', 'P1, m3, M3, P4, P5, m6, m7', 356),
('Raga Darbar', 'P1, M2, P4, P5, M6, m7', 356),
('Raga Desh', 'P1, M2, P4, P5, M7', 356),
('Raga Deshgaur', 'P1, m2, P5, m6, M7', 356),
('Raga Deshi', 'P1, M2, m3, P4, P5, m6, M6, m7', 356),
('Raga Deshi 2', 'P1, M2, m3, P4, P5, M6, M7', 356),
('Raga Deshi 3', 'P1, M2, m3, P4, P5, m6, M7', 356),
('Raga Desh Malhar', 'P1, M2, M3, P4, P5, M6, m7, M7', 356),
('Raga Desisimharavam', 'P1, M2, m3, d5, P5, M6, m7', 356),
('Raga Deskar', 'P1, M2, M3, P5, M6', 356),
('Raga Desya Todi', 'P1, m3, P4, P5, m6, m7', 356),
('Raga Devakriya', 'P1, M2, P4, P5, M6', 356),
('Raga Devamani', 'P1, m3, M3, P5, m6, M7', 356),
('Raga Devarangini', 'P1, M2, M3, P4, P5, M6', 356),
('Raga Devaranjani 2 (Devaranji)', 'P1, P4, P5, m6, M7', 356),
('Raga Devarashtra', 'P1, m3, M3, d5, P5, m6, M7', 356),
('Raga Dhani (Suddha Dhanyasi)', 'P1, m3, P4, P5, m7', 356),
('Raga Dhauta Pancama', 'P1, m3, M3, d5, P5, m6, M7', 356),
('Raga Dhavalangam', 'P1, m2, M3, d5, P5, m6', 356),
('Raga Dhavalashri', 'P1, M3, A4, P5, M6', 356),
('Raga Dhipaka', 'P1, m2, M3, d5, P5, m6, M7', 356),
('Raga Dhunibinnashadjam', 'P1, m2, m3, P4, P5, m6, M7', 356),
('Raga Dipak', 'P1, M2, M3, P4, d5, P5', 356),
('Raga Dumyaraga', 'P1, M2, m3, d5, P5, M6, M7', 356),
('Raga Durga', 'P1, M2, P4, P5, M6', 356),
('Raga Dvigandharabushini', 'P1, M2, m3, M3, P5, m6, M6', 356),
('Raga Gamakakriya', 'P1, m2, M3, d5, P5, M7', 356),
('Raga Gamakasamantam', 'P1, m2, m3, d5, P5, m6, M7', 356),
('Raga Gambhiranata', 'P1, M3, P4, P5, M7', 356),
('Raga Ganasamavarali', 'P1, m2, M2, P4, P5, m6, M7', 356),
('Raga Ganavaridhi', 'P1, m3, M3, P4, P5, M6, m7', 356),
('Raga Gandharavam', 'P1, m2, m3, P4, P5, m7', 356),
('Raga Gangatarangini', 'P1, M3, P4, d5, m6, M7', 356),
('Raga Gaud Sarang', 'P1, M2, M3, P4, A4, P5, M6, M7', 356),
('Raga Gaula', 'P1, m2, M3, P4, P5, m7', 356),
('Raga Gauri', 'P1, m2, P4, P5, M7', 356),
('Raga Gaurikriya', 'P1, m3, d5, P5, m7, M7', 356),
('Raga Gauri Velavali', 'P1, M2, m3, P4, P5, M6', 356),
('Raga Geyahejjajji', 'P1, m2, M3, P4, P5, m6', 356),
('Raga Ghandarva', 'P1, m2, M2, d5, P5, m7, M7', 356),
('Raga Ghantana', 'P1, M2, m3, P4, m6, M7', 356),
('Raga Girija', 'P1, M3, P4, m6, M7', 356),
('Raga Girvani', 'P1, m2, m3, d5, P5, m6, M6', 356),
('Raga Gitapriya', 'P1, M2, M3, d5, P5, m6, M7', 356),
('Raga Gopikatilaka', 'P1, M2, m3, d5, P5, m7', 356),
('Raga Gopikavasantam', 'P1, m3, P4, P5, m6, m7', 356),
('Raga Gopriya', 'P1, M2, M3, d5, m6, m7', 356),
('Raga Gorakh Kalyan', 'P1, M2, P4, P5, M6, m7', 356),
('Raga Gowleeswari', 'P1, m2, P4, m6', 356),
('Raga Guhamanohari', 'P1, M2, P4, M6, m7', 356),
('Raga Gunakri (Gunakali)', 'P1, m2, P4, P5, m6', 356),
('Raga Gurjari Todi', 'P1, m2, m3, d5, m6, m7', 356),
('Raga Hamir Kalyani', 'P1, M2, M3, P4, d5, P5, M6, M7', 356),
('Raga Hamsadhvani (Hansadhvani)', 'P1, M2, M3, P5, M7', 356),
('Raga Hamsagiri', 'P1, m3, M3, d5, P5, m7, M7', 356),
('Raga Hamsalata', 'P1, M2, M3, d5, P5, m6, M7', 356),
('Raga Hamsanada', 'P1, M2, A4, P5, M7', 356),
('Raga Hamsanandi', 'P1, m2, M3, d5, M6, M7', 356),
('Raga Hamsanandi 2', 'P1, m2, M3, P4, m6, M7', 356),
('Raga Hamsanarayani', 'P1, m2, M3, d5, P5, M7', 356),
('Raga Hamsa Vinodini', 'P1, M2, M3, P4, M6, M7', 356),
('Raga Hansadhvani (Hamsadhvani)', 'P1, M2, M3, P5, M7', 356),
('Raga Harikauns', 'P1, m3, d5, m6, m7', 356),
('Raga Hari Nata', 'P1, M3, P4, P5, M6, M7', 356),
('Raga Harini', 'P1, M2, M3, P4, P5, M6, m7', 356),
('Raga Haripriya', 'P1, M2, M3, P4, P5, m6, M7', 356),
('Raga Haripriya 2', 'P1, M2, P4, m6', 356),
('Raga Hejjajji', 'P1, m2, M3, d5, m6, M6', 356),
('Raga Hindol (Sunada Vinodini)', 'P1, M3, A4, M6, M7', 356),
('Raga Hindola', 'P1, m3, P4, m6, m7', 356),
('Raga Hindolita', 'P1, M3, P4, M6, M7', 356),
('Raga Indupriya', 'P1, m2, M3, d5, P5, m6', 356),
('Raga Jaganmohanam', 'P1, M2, d5, P5, m6, m7', 356),
('Raga Jait Kalyan', 'P1, M2, M3, P5, M6', 356),
('Raga Janasammodini', 'P1, M2, M3, P5, m6', 356),
('Raga Janjhuti', 'P1, M2, M3, P4, P5, M6, m7', 356),
('Raga Jaunpuri', 'P1, M2, m3, P4, P5, m6, m7', 356),
('Raga Jayakauns', 'P1, m3, P4, d5, m7', 356),
('Raga Jeyasuddhamalavi', 'P1, m2, M3, P4, P5, m7, M7', 356),
('Raga Jhankara Bhramavi', 'P1, M2, m3, P4, P5, m6, M6', 356),
('Raga Jinavali', 'P1, m2, M2, d5, P5, m6, M7', 356),
('Raga Jivantika', 'P1, m2, P4, P5, M6, M7', 356),
('Raga Jivantini', 'P1, m3, d5, P5, m7, M7', 356),
('Raga Jog', 'P1, m3, M3, P4, P5, m7', 356),
('Raga Jogiya', 'P1, m2, M3, P4, P5, m6, m7', 356),
('Raga Jotismatti', 'P1, m3, M3, d5, P5, m6, m7', 356),
('Raga Jyoti', 'P1, M3, d5, P5, m6, m7', 356),
('Raga Kaikavasi', 'P1, M2, m3, d5, P5, m7, M7', 356),
('Raga Kaishikiranjani (Kaushiranjani)', 'P1, M2, m3, P4, m6, M7', 356),
('Raga Kalagada', 'P1, m2, M3, P5, m6, M6', 356),
('Raga Kalahamsa', 'P1, m3, M3, P4, P5, m6, M6', 356),
('Raga Kalakanthi', 'P1, m2, M3, P4, P5, m6, M6', 356),
('Raga Kalakanthi 2', 'P1, m2, P4, P5, m6, M6', 356),
('Raga Kalamurti', 'P1, m2, m3, d5, P5, m6, m7', 356),
('Raga Kalavati', 'P1, m2, M3, P4, P5, M6', 356),
('Raga Kalingada', 'P1, m2, M3, P4, P5, m6, M7', 356),
('Raga Kalyana', 'P1, M2, m3, P4, P5, m6, M7', 356),
('Raga Kalyani Keseri', 'P1, M2, M3, A4, P5, M6', 356),
('Raga Kamalamanohari', 'P1, M3, P4, P5, m6, M7', 356),
('Raga Kamalamanohari 2', 'P1, M3, P4, P5, m6, m7', 356),
('Raga Kambhoji', 'P1, M2, M3, P4, P5, M6', 356),
('Raga Kanakambari', 'P1, m2, M2, P4, P5, m6, M6', 356),
('Raga Kannadabangala', 'P1, m2, M3, P4, P5, m6', 356),
('Raga Kapijingla', 'P1, M2, m3, P4, M6, m7', 356),
('Raga Kashyapi', 'P1, m2, m3, P5, m6, m7', 356),
('Raga Kasiramakriya', 'P1, m2, M3, d5, P5, m6, M7', 356),
('Raga Kaushikdhvani', 'P1, M3, P4, M6, M7', 356),
('Raga Kaushiranjani / Kaishikiranjani', 'P1, M2, m3, P4, m6, M7', 356),
('Raga Kedar', 'P1, M2, M3, P4, d5, P5, M6, M7', 356),
('Raga Kedaram', 'P1, M2, M3, P4, P5, M7', 356),
('Raga Keseri', 'P1, M2, M3, P4, P5, m6, M6', 356),
('Raga Khamaj', 'P1, M2, M3, P4, P5, M6, m7, M7', 356),
('Raga Khamaji Durga', 'P1, M3, P4, M6, m7', 356),
('Raga Khamas', 'P1, M3, P4, P5, M6, m7', 356),
('Raga Khambhavati', 'P1, M2, M3, P4, P5, M6, m7', 356),
('Raga Kiranavali', 'P1, M2, m3, P4, P5, m6, M7', 356),
('Raga Kirvani', 'P1, M2, m3, P4, P5, m6, M7', 356),
('Raga Kokila', 'P1, M2, M3, P5, M6', 356),
('Raga Kokilaravam', 'P1, m2, m3, P4, P5, M6, M7', 356),
('Raga Kokil Pancham', 'P1, m3, P4, P5, m6', 356),
('Raga Kshanika', 'P1, m2, P4, m6, M7', 356),
('Raga Kumarapriya', 'P1, m2, M2, m6, M7', 356),
('Raga Kumbhini', 'P1, m2, M2, d5, P5, M6, M7', 356),
('Raga Kumud', 'P1, M2, M3, P5, M6, M7', 356),
('Raga Kumudki (Kumurdaki)', 'P1, M2, M3, d5, M7', 356),
('Raga Kuntala', 'P1, M2, M3, d5, P5, m6, M6', 356),
('Raga Kuntalavarali (Kuntvarali)', 'P1, P4, P5, M6, m7', 356),
('Raga Kusumakaram', 'P1, m3, M3, d5, P5, M6, M7', 356),
('Raga Lalit', 'P1, m2, M3, P4, d5, M6, M7', 356),
('Raga Lalita', 'P1, m2, M3, P4, d5, m6, M7', 356),
('Raga Lalita 2', 'P1, m2, M3, P4, m6, M7', 356),
('Raga Lalit Bhairav', 'P1, m2, M3, P4, m6, m7', 356),
('Raga Lasaki', 'P1, m2, P4, P5, m7', 356),
('Raga Latantapriya', 'P1, m2, P4, P5, m6', 356),
('Raga Latika', 'P1, M2, M3, P5, m6, M7', 356),
('Raga Lavangi', 'P1, m2, P5, m7', 356),
('Raga Madhava Manohari', 'P1, M2, m3, d5, P5, m6, M7', 356),
('Raga Madhmat Sarang', 'P1, M2, P4, P5, m7', 356),
('Raga Madhukauns (hexatonic)', 'P1, m3, d5, P5, M6, m7', 356),
('Raga Madhukauns (pentatonic)', 'P1, m3, d5, P5, m7', 356),
('Raga Madhuranjani', 'P1, m3, P4, P5, M7', 356),
('Raga Madhuri', 'P1, M3, P4, P5, M6, m7, M7', 356),
('Raga Madhuvanti', 'P1, M2, m3, d5, P5, M6, M7', 356),
('Raga Madhyamavati', 'P1, M2, P4, P5, m7', 356),
('Raga Mahathi', 'P1, M3, P5, m7', 356),
('Raga Malahari', 'P1, m2, M3, P4, P5, m6', 356),
('Raga Malarani (Hamsanada)', 'P1, M2, d5, P5, m7, M7', 356),
('Raga Malashri', 'P1, M3, A4, P5, M7', 356),
('Raga Malashri 2', 'P1, M3, P5', 356),
('Raga Malayamarutam', 'P1, m2, M3, P5, M6, m7', 356),
('Raga Malgunji', 'P1, M2, m3, M3, P4, P5, M6, m7, M7', 356),
('Raga Malkauns (Malakosh)', 'P1, m3, P4, m6, m7, d8', 356),
('Raga Malini', 'P1, m2, m3, P4, P5, m6, M6', 356),
('Raga Mamata', 'P1, M3, P5, M6, M7', 356),
('Raga Manaranjani I', 'P1, m2, M3, P5, m7', 356),
('Raga Manaranjani II', 'P1, m2, P4, P5, M6', 356),
('Raga Manavi', 'P1, M2, m3, P5, M6, m7', 356),
('Raga Mand', 'P1, M3, P4, P5, M6', 356),
('Raga Mandari', 'P1, m2, M3, d5, P5, M7', 356),
('Raga Manirangu', 'P1, M2, m3, P4, P5, m7', 356),
('Raga Manohari', 'P1, m3, P4, P5, M6, m7', 356),
('Raga Manoranjani', 'P1, m2, M2, P4, P5, M6, M7', 356),
('Raga Marga Hindola', 'P1, m3, P4, M6, M7', 356),
('Raga Matha Kokila (Matkokil)', 'P1, M2, P5, M6, m7', 356),
('Raga Megh', 'P1, M2, P4, P5, m7', 356),
('Raga Megh (Megh Malhar)', 'P1, M2, P4, P5, m7, M7', 356),
('Raga Megharanjani', 'P1, m2, M3, P4, m6', 356),
('Raga Megharanji', 'P1, m2, M3, P4, M7', 356),
('Raga Mian Ki Malhar', 'P1, M2, m3, P4, P5, M6, m7, M7', 356),
('Raga Mohanam', 'P1, M2, M3, P5, M6', 356),
('Raga Mohanangi', 'P1, m3, M3, P5, M6', 356),
('Raga Mruganandana', 'P1, M2, M3, A4, M6, M7', 356),
('Raga Mukhari', 'P1, M2, m3, P4, P5, m6, M6, m7', 356),
('Raga Multani', 'P1, m2, m3, d5, P5, m6, M7', 356),
('Raga Multani 2', 'P1, m3, d5, P5, M7', 356),
('Raga Nabhomani', 'P1, m2, M2, d5, P5, M6, m7', 356),
('Raga Nabhomani 2', 'P1, m2, M2, d5, P5', 356),
('Raga Nagabharanam', 'P1, M2, M3, P4, P5, m7, M7', 356),
('Raga Nagagandhari', 'P1, M2, P4, P5, M6, M7', 356),
('Raga Nagasvaravali', 'P1, M3, P4, P5, M6', 356),
('Raga Nalinakanti', 'P1, M2, M3, P4, P5, M7', 356),
('Raga Nandkauns', 'P1, m3, M3, P4, P5, M6, m7', 356),
('Raga Narayani', 'P1, M2, P4, P5, M6, m7', 356),
('Raga Narmada', 'P1, m2, M3, d5, P5, m6, m7', 356),
('Raga Nasamani', 'P1, m3, M3, d5, P5, M6, m7', 356),
('Raga Nata', 'P1, m3, P4, P5, M7', 356),
('Raga Natabharanam', 'P1, m2, m3, P4, P5, M6, m7', 356),
('Raga Nattaikurinji', 'P1, M2, M3, P4, M6, m7', 356),
('Raga Navamanohari', 'P1, M2, P4, P5, m6, m7', 356),
('Raga Nayaki', 'P1, M2, m3, P4, P5, m7', 356),
('Raga Nayaki Kanada', 'P1, M2, m3, P4, P5, M6, m7', 356),
('Raga Neelangi', 'P1, M2, m3, d5, m6, M6', 356),
('Raga Neroshta', 'P1, M2, M3, M6, M7', 356),
('Raga Nigamagamini', 'P1, M3, d5, M7', 356),
('Raga Nileshwari', 'P1, m3, P4, d5, P5, m7', 356),
('Raga Nisada', 'P1, M2, m3, d5, P5, m7, M7', 356),
('Raga Nishadi', 'P1, M2, A4, P5, M6, M7', 356),
('Raga None', 'P1, m3, M3, P4, P5, m7, M7', 356),
('Raga Ongkari', 'P1, A4, P5', 356),
('Raga Padi', 'P1, m2, P4, P5, m6, M7', 356),
('Raga Pahadi', 'P1, M2, M3, P4, P5, m6, M6, m7, M7', 356),
('Raga Palasi', 'P1, M2, m3, P4, P5, m7', 356),
('Raga Pancama', 'P1, m2, M3, d5, M6, M7', 356),
('Raga Pantuvarali', 'P1, m2, M3, d5, P5, m6, M7', 356),
('Raga Paraj', 'P1, m2, M3, P4, P5, m6, M7', 356),
('Raga Paraju', 'P1, M3, P4, P5, m6, M7', 356),
('Raga Partiravam', 'P1, m2, M3, d5, P5, M6, M7', 356),
('Raga Patdip', 'P1, M2, m3, P4, P5, M6, M7', 356),
('Raga Phenadyuti', 'P1, m2, M2, P4, P5, m6, m7', 356),
('Raga Phenadyuti 2', 'P1, m2, P4, P5, m6, m7', 356),
('Raga Pilu', 'P1, M2, m3, P4, P5, m6, M6, m7, M7', 356),
('Raga Pratapa', 'P1, m2, M3, d5, P5, m6, m7', 356),
('Raga Priyadharshini', 'P1, M2, P4, m6, M7', 356),
('Raga Puriya', 'P1, m2, M3, d5, P5, M6, M7', 356),
('Raga Puriya 2', 'P1, m2, M3, d5, M6, M7', 356),
('Raga Puriya Dhanashri', 'P1, m2, M3, d5, P5, m6, M7', 356),
('Raga Puriya Kalyan', 'P1, m2, M3, d5, P5, M6, M7', 356),
('Raga Purnalalita', 'P1, M2, m3, P4, P5', 356),
('Raga Purna Pancama', 'P1, m2, M3, P4, P5, m6', 356),
('Raga Puruhutika', 'P1, P4, P5, M6, M7', 356),
('Raga Purvaholika', 'P1, P4, P5, M6, M7', 356),
('Raga Pushpalithika', 'P1, M2, m3, P4, P5, m7', 356),
('Raga Putrika', 'P1, m2, M2, m6, M6', 356),
('Raga Ragamalini', 'P1, m2, M3, P4, P5, M6', 356),
('Raga Rageshri', 'P1, M2, M3, P4, M6, m7, M7', 356),
('Raga Rageshri 2 (Rageshwari)', 'P1, M2, M3, P4, M6, m7', 356),
('Raga Rajeshwari', 'P1, m3, P4, M6, M7', 356),
('Raga Ramamanohari', 'P1, m2, M3, d5, P5, M6, m7', 356),
('Raga Ramamanohari 2', 'P1, M3, P4, P5, m6, M7', 356),
('Raga Ramdasi Malhar', 'P1, M2, m3, M3, P4, P5, M6, m7, M7', 356),
('Raga Ramkali (Ramakri)', 'P1, m2, M3, P4, d5, P5, m6, M7', 356),
('Raga Ramkali 2', 'P1, m2, m3, P5, m6', 356),
('Raga Rangini', 'P1, M2, m3, d5, M6, M7', 356),
('Raga Ranjani', 'P1, M2, m3, d5, M6, M7', 356),
('Raga Rasamanjari', 'P1, m3, M3, d5, P5, m7, M7', 356),
('Raga Rasamanjari 2', 'P1, m3, M3, d5, P5, M7', 356),
('Raga Rasavali', 'P1, m2, P4, P5, M6, m7', 356),
('Raga Rasika Ranjani', 'P1, m2, M3, P5, M6', 356),
('Raga Rasranjani', 'P1, M2, P4, M6, M7', 356),
('Raga Ratipriya', 'P1, M2, M3, d5, P5, m6, m7', 356),
('Raga Ratnakanthi', 'P1, M2, M3, A4, P5, M7', 356),
('Raga Ravikriya', 'P1, m2, M2, d5, P5, m7, M7', 356),
('Raga Reva', 'P1, m2, M3, P5, m6', 356),
('Raga Revagupti', 'P1, m2, M3, P5, m6', 356),
('Raga Rudra Pancama', 'P1, m2, M3, P4, M6, m7', 356),
('Raga Rukmangi', 'P1, m2, m3, P5, m7', 356),
('Raga Sahera', 'P1, M2, M3, A4, A5, A6', 356),
('Raga Sailadesakshi', 'P1, m3, M3, P4, P5, M6, M7', 356),
('Raga Salagavarali', 'P1, m2, m3, P5, M6, m7', 356),
('Raga Salanganata', 'P1, m2, P4, P5, m6', 356),
('Raga Samanta', 'P1, M2, M3, P4, P5, m7, M7', 356),
('Raga Samudhra Priya', 'P1, m3, d5, P5, m7', 356),
('Raga Sanjh ka Hindol', 'P1, M3, A4, M6, M7', 356),
('Raga Sankara (Shankara)', 'P1, M2, M3, P5, M6, M7', 356),
('Raga Santanamanjari', 'P1, m3, M3, d5, P5, m6, M6', 356),
('Raga Sarasanana', 'P1, M2, M3, P4, m6, M7', 356),
('Raga Sarasvati', 'P1, M2, A4, P5, M6, m7', 356),
('Raga Saravati (Sharavati)', 'P1, M3, P4, P5, m6, M6', 356),
('Raga Sarvasri', 'P1, P4, P5', 356),
('Raga Saugandhini', 'P1, m2, d5, P5, m6', 356),
('Raga Saurashtra', 'P1, m2, M3, P4, P5, m6, M6, M7', 356),
('Raga Sauviram', 'P1, m2, m3, d5, P5, M6, M7', 356),
('Raga Saveri', 'P1, m2, P4, P5, m6', 356),
('Raga Savitri', 'P1, M3, P4, P5, m7, M7', 356),
('Raga Senagrani', 'P1, m2, m3, P4, P5, m6, M6', 356),
('Raga Shailaja', 'P1, m3, P5, m6, m7', 356),
('Raga Shankarabharanam', 'P1, M2, M3, P4, P5, M6, M7', 356),
('Raga Shilangi', 'P1, M3, A4, P5, M7', 356),
('Raga Shobhavari', 'P1, M2, P4, P5, m6', 356),
('Raga Shri', 'P1, m2, M3, d5, P5, m6, M7', 356),
('Raga Shri Kalyan', 'P1, M2, A4, P5, M6', 356),
('Raga Shubravarni', 'P1, M2, d5, M6, m7', 356),
('Raga Shuddh Kalyan', 'P1, M2, M3, A4, P5, M6, M7', 356),
('Raga Simharava (Sinharavam)', 'P1, M2, m3, d5, P5, m7', 356),
('Raga Simhavahini', 'P1, M3, P4, P5, m6, M7', 356),
('Raga Sindhi-Bhairavi', 'P1, m2, M2, m3, M3, P4, P5, m6, m7, M7',  356),
('Raga Sindhura', 'P1, M2, m3, P4, P5, M6, m7, M7', 356),
('Raga Sindhu Ramakriya', 'P1, M3, P4, P5, m6, M7', 356),
('Raga Sindhura Kafi', 'P1, M2, m3, P4, P5, M7', 356),
('Raga Siva Kambhoji', 'P1, M2, M3, P4, P5, m7', 356),
('Raga Sivaranjini (Shivranjani)', 'P1, M2, m3, P5, M6', 356),
('Raga Sohani', 'P1, m2, M3, d5, P5, M6, M7', 356),
('Raga Sohini', 'P1, m2, M3, P4, m6, M7', 356),
('Raga Sorati', 'P1, M2, P4, P5, M6, m7, M7', 356),
('Raga Sowrashtram', 'P1, m2, M3, P4, P5, M6, M7', 356),
('Raga Sriraga', 'P1, M2, m3, P4, P5, M6, m7', 356),
('Raga Sriranjani', 'P1, M2, m3, P4, M6, m7', 356),
('Raga Srutiranjani', 'P1, M2, M3, d5, P5, m6, M6', 356),
('Raga Sthavarajam', 'P1, m2, m3, d5, P5, M6, m7', 356),
('Raga Suddha Bangala', 'P1, M2, m3, P4, P5, M6', 356),
('Raga Suddha Dhanyasi (Dhani)', 'P1, m3, P4, P5, m7', 356),
('Raga Suddha Mukhari', 'P1, m2, M2, P4, m6, M6', 356),
('Raga Suddha Pancama', 'P1, m2, M3, P4, d5, m6, M7', 356),
('Raga Suddha Ramakriya', 'P1, m2, M3, d5, P5, m6, M7', 356),
('Raga Suddha Saveri', 'P1, M2, P4, P5, M6', 356),
('Raga Suddha Simantini', 'P1, m2, m3, P4, P5, m6', 356),
('Raga Suddha Todi', 'P1, m2, m3, P4, m6, m7', 356),
('Raga Suha Kanada', 'P1, P5, m6, m7', 356),
('Raga Suha Sughrai', 'P1, M2, m3, P4, P5, m7', 356),
('Raga Sumukam', 'P1, M2, A4, M7', 356),
('Raga Suposhini', 'P1, M2, P4, P5, M6, m7', 356),
('Raga Supradhipam', 'P1, m2, M3, P4, P5, M6, M7', 356),
('Raga Surati', 'P1, M2, M3, P4, P5, M6, m7', 356),
('Raga Sur Malhar', 'P1, M2, P4, P5, M6, m7, M7', 356),
('Raga Surya', 'P1, m3, P4, M6, m7', 356),
('Raga Sutradhari', 'P1, M2, P4, P5, m6', 356),
('Raga Syamalam', 'P1, M2, m3, d5, P5, m6', 356),
('Raga Takka', 'P1, m3, P4, P5, m6, M7', 356),
('Raga Tanukirti', 'P1, m2, M2, P4, P5, m7, M7', 356),
('Raga Tarangini', 'P1, M2, M3, P4, P5, m6, m7', 356),
('Raga Tilang', 'P1, M3, P4, P5, m7, M7', 356),
('Raga Tivravahini', 'P1, m2, m3, d5, P5, M6, m7', 356),
('Raga Trimurti', 'P1, M2, m3, P5, m6, m7', 356),
('Raga Trishuli', 'P1, m3, M3, P4, P5, M6, M7', 356),
('Raga Udayaravicandrika', 'P1, m3, P4, P5, M7', 356),
('Raga Udhayaravi Chandrika', 'P1, m3, P4, P5, m7', 356),
('Raga Vaijayanti', 'P1, M2, A4, P5, M7', 356),
('Raga Valaji', 'P1, M3, P5, M6, m7', 356),
('Raga Vamsavathi', 'P1, m2, m3, d5, P5, m7, M7', 356),
('Raga Varali', 'P1, m2, M2, d5, P5, m6, M7', 356),
('Raga Varamu', 'P1, m3, P4, M6, m7', 356),
('Raga Varini', 'P1, m3, P5, m6, m7', 356),
('Raga Vasantha', 'P1, M2, m3, P4, P5, m6, M7', 356),
('Raga Vasantha 2', 'P1, m2, M3, P4, M6, M7', 356),
('Raga Vasantha (bhairavi)', 'P1, m2, M3, P4, m6, m7', 356),
('Raga Vativasanta (bhairavi)', 'P1, m2, M3, P4, P5, m6, m7', 356),
('Raga Vegavahini', 'P1, m2, M3, P4, P5, M6, m7', 356),
('Raga Velavali', 'P1, M2, m3, P4, P5, M6, M7', 356),
('Raga Vibhas (bhairava)', 'P1, m2, M3, P5, m6', 356),
('Raga Vibhas (marva)', 'P1, m2, M3, P5, M6', 356),
('Raga Vibhavari (Revati)', 'P1, m2, P4, P5, m7', 356),
('Raga Vijayanagari', 'P1, M2, m3, d5, P5, M6', 356),
('Raga Vijayasri', 'P1, m2, M2, d5, P5, M7', 356),
('Raga Vijayavasanta', 'P1, M3, A4, P5, m7, M7', 356),
('Raga Vilasini', 'P1, M2, M3, P4, P5, M7', 356),
('Raga Viravasantham', 'P1, M2, m3, P4, P5, m7, M7', 356),
('Raga Vivardhini', 'P1, M2, M3, P4, P5, m7', 356),
('Raga Viyogavarali', 'P1, m2, m3, P4, m6, M7', 356),
('Raga Vutari', 'P1, M3, A4, P5, M6, m7', 356),
('Raga Yaman Kalyan', 'P1, M2, M3, P4, d5, P5, M6, M7', 356),
('Raga Yamuna Kalyani', 'P1, M2, M3, A4, P5, M6', 356),
('Raga Yashranjani', 'P1, m2, d5, P5, m6', 356),
('Raga Zilaf', 'P1, M3, P4, P5, m6', 356),
('Raga Zilla', 'P1, M2, m3, M3, P4, P5, M6, m7', 356),
('Rast', 'P1, M2, M3, P4, P5, M6, M7', 51),
('Rast ascending', 'P1, M2, M3, P4, P5, M6, M7', 300),
('Rast descending', 'P8, m7, M6, P5, P4, M3, M2', 300),
('Ravel', 'P1, m2, m3, d4, d5, m6, m7', NULL),
('Relative Minor Pentatonic', 'P1, m3, P4, P5, m7', NULL),
('Ritsu', 'P1, m2, m3, P4, m6, m7', 392),
('Ritsu Gagaku', 'P1, M2, P4, P5, M6', 392),
('Ritsusen', 'P1, M2, P4, P5, M6', 392),
('Romanian Major', 'P1, m2, M3, A4, P5, M6, m7', 642),
('Romanian Minor', 'P1, M2, m3, A4, P5, M6, m7', 642),
('Rui Bin', 'P1, M2, P4, P5, m7', 156),
('Ryo', 'P1, M2, M3, P4, d5, P5, M6, m7, M7', 392),
('Ryosen', 'P1, M2, M3, P5, M6', 392),
('Ryukyu', 'P1, M3, P4, P5, M7', 392),
('Sabach Minor', 'P1, M2, m3, M3, P5, m6, m7', 300),
('Sakura', 'P1, m2, P4, P5, m6', 392),
('Sansagari', 'P1, P4, A6', 392),
('Scottish Hexatonic', 'P1, M2, M3, P4, P5, M6', 826), -- Scotland
('Scottish Pentatonic', 'P1, M2, P4, P5, M6', 826), -- Scotland
('Scriabin', 'P1, m2, M3, P5, M6', 826), -- Scotland
('Se', 'P1, M2, m3, P4, P5, m6, m7', 156),
('Sengah', 'P1, m3, M3, P4, P5, m6, M7', 300),
('Shang', 'P1, M2, M3, P4, P5, M6, m7', 156),
('Shang 2', 'P1, M2, P4, P5, m7', 156),
('Shostakovich', 'P1, m2, m3, M3, d5, P5, M6, M7', 643),
('Souzinak Minor', 'P1, M2, m3, d5, P5, M6, m7', 300),
('Spanish Gypsy', 'P1, m2, M3, P4, P5, m6, m7', 724),
('Spanish Major', 'P1, m2, M3, P4, P5, m6, m7', 724),
('Spanish Mode', 'P1, m2, m3, P4, P5, m6, m7', 724),
('Spanish Phrygian I', 'P1, m2, M3, P4, P5, m6, m7', 724),
('Spanish Phrygian II', 'P1, m2, m3, M3, P4, P5, m6, m7', 724),
('Spanish Phrygian III', 'P1, M2, m3, A4, P5, M6, m7', 724),
('Super Locrian', 'P1, m2, m3, d4, d5, m6, m7', NULL),
('Super Locrian Diminished', 'P1, m2, m3, d4, d5, m6, d7', NULL),
('Suspended Pentatonic', 'P1, M2, P4, P5, m7', NULL),
('Symmetrical Diminished', 'P1, m2, m3, M3, A4, P5, M6, m7', NULL),
('Symmetrical Decatonic', 'P1, m2, M2, M3, P4, d5, P5, m6, m7, M7',  NULL),
('Synthetic Mixture #5', 'P1, M2, M3, A4, A5, M6, m7', NULL),
('Syrian pentatonic', 'P1, m2, M3, P4, m6', 760),
('Tabahaniotiko', 'P1, M2, M3, P4, P5, m6, M7', 300),
('Taishikicho', 'P1, M2, M3, P4, d5, P5, M6, m7, M7', 392),
('Takemitsu Tree Line mode 1', 'P1, M2, m3, d5, m6, M7', 392),
('Takemitsu Tree Line mode 2', 'P1, M2, m3, d5, m6, m7', 392),
('Tcherepnin Nine-Note Mode I', 'P1, m2, m3, M3, P4, P5, m6, M6, M7', 643),
('Tcherepnin Major Pentatonic Mode I', 'P1, M2, M3, P5, M6', 643),
('Tcherepnin Major Pentatonic Mode II', 'P1, M2, P4, P5, M7', 643),
('Tcherepnin Major Pentatonic Mode III', 'P1, m3, P4, m6, m7', 643),
('Tcherepnin Major Pentatonic Mode IV', 'P1, M2, P4, P5, M6', 643),
('Tcherepnin Major Pentatonic Mode V', 'P1, m3, P4, P5, m7', 643),
('Tcherepnin Minor Pentatonic Mode I', 'P1, M2, m3, P5, m6', 643),
('Tcherepnin Minor Pentatonic Mode II', 'P1, m2, P4, d5, m7', 643),
('Tcherepnin Minor Pentatonic Mode III', 'P1, M3, P4, M6, M7', 643),
('Tcherepnin Minor Pentatonic Mode IV', 'P1, m2, P4, P5, m6', 643),
('Tcherepnin Minor Pentatonic Mode V', 'P1, M3, A4, P5, M7', 643),
('Tetrardus authenticus', 'P1, M2, M3, P4, P5, M6, m7', NULL),
('Tetrardus plagis', 'P1, M2, m3, P4, P5, M6, m7', NULL),
('Three Semitone', 'P1, A2, A4, M6', NULL),
('Tizita Major', 'P1, M2, M3, P5, M6', 230),
('Tizita Minor', 'P1, m2, m3, P5, m6', 230),
('Todi Thaat', 'P1, m2, m3, P4, P5, m6, m7', 392),
('Todi Thaat 2', 'P1, m2, m3, d5, P5, m6, M7', 392),
('Tritus authenticus', 'P1, m2, m3, d5, P5, m6, M7', NULL),
('Tritus plagis', 'P1, M2, M3, P4, P5, M6, M7', NULL),
('Tsinganikos', 'P1, m2, M3, P4, d5, M6, m7', 300),
('Tunisian', 'P1, M2, m3, d5, P5, M6, m7', 788),
('Ultra Locrian', 'P1, m2, m3, d4, d5, m6, d7', NULL),
('Ujo', 'P1, M2, P4, P5, M6', 410),
('Ute tritonic', 'P1, m3, m7', 3), -- Native North American
('Utility Minor', 'P1, M2, m3, P4, P5, m6, m7, M7', NULL),
('Van der Horst Octatonic', 'P1, m2, m3, P4, d5, P5, M6, M7', 208),
('Verdi''s Enigmatic ascending', 'P1, m2, M3, d5, m6, m7, M7', 380),
('Verdi''s Enigmatic descending', 'P8, M7, m6, P5, M3, M2, m2', 380),
('Verdi''s Scala Enigmatica', 'P1, m2, M3, P4, d5, m6, m7, M7', 380),
('Warao ditonic', 'P1, A6', 5), -- Native South American
('Warao tetratonic', 'P1, M2, m3, m7', 5), -- Native South American
('Warao tritonic', 'P1, P4, P5', 5), -- Native South American
('Whole-Half step', 'P1, M2, m3, P4, d5, m6, M6, M7', NULL),
('Whole-Half tone', 'P1, M2, m3, P4, d5, m6, M6, M7', NULL),
('Whole-tone I', 'P1, M2, M3, A4, A5, A6', NULL),
('Xin', 'P1, M2, M3, P4, P5, M6, M7', 156),
('Yishtabach', 'P1, m2, m3, P4, d5, m6, m7', 376), -- Israeli
('Yi Ze', 'P1, m3, P4, m6, m7', 156),
('Yo (shomyo, Gagaku)', 'P1, M2, P4, P5, M6', 392),
('Yona Nuki Major', 'P1, M2, M3, P5, M6', 392),
('Yona Nuki Minor', 'P1, M2, m3, P5, m6', 392),
('Yosen', 'P1, M2, P4, P5, M6, m7', 392),
('Youlan', 'P1, m2, M2, M3, P4, d5, P5, M6, m7', 156),
('Yu', 'P1, M2, m3, P4, P5, M6, m7', 156),
('Yu 2', 'P1, m3, P4, P5, m7', 156),
('Zhalibny Minor', 'P1, M2, m3, P4, P5, m6, M7', NULL),
('Zheng', 'P1, M2, P4, P5, M6', 156),
('Zhi', 'P1, M2, P4, P5, M6', 156),
('Zirafkend : Arabic', 'P1, M2, m3, P4, P5, m6, M6, M7', 682),
('Zokuso', 'P1, m2, m3, P4, P5, m6, m7', 392);

-- Prevent user from editing preset scale data (except for tags)
UPDATE SCALE_TYPE SET PRESET = 1;

-- CREATE TRIGGER TRIG_SCALE_TYPE_NO_UPDATE_PRESETS BEFORE UPDATE ON SCALE_TYPE
-- BEGIN
--     SELECT
--         (CASE
--              WHEN OLD.PRESET = 1 AND (NEW.NAME <> OLD.NAME OR NEW.INTERVALS <> OLD.INTERVALS OR NEW.SIZE <> OLD.SIZE OR NEW.ORIGIN <> OLD.ORIGIN OR NEW.PRESET <> OLD.PRESET)
--                  THEN RAISE(ABORT, 'Cannot change fields on preset scale types')
--             END)
--     FROM SCALE_TYPE;
-- END;

-- CREATE TRIGGER TRIG_SCALE_TYPE_NO_DELETE_PRESETS BEFORE DELETE ON SCALE_TYPE
-- BEGIN
--     SELECT
--         (CASE
--              WHEN OLD.PRESET = 1
--                  THEN RAISE(ABORT, 'Cannot delete preset scale types')
--             END)
--     FROM SCALE_TYPE;
-- END;



-- Default (starter) tags
INSERT INTO TAG(NAME) VALUES
('Jewish'),
('Balinese'),
('North Indian'),
('Byzantine'),
('Alaskan'),
('Latin'),
('Church'),
('Hawaiian'),
('South Indian'),
('Persian'),
('Scotland'),
('Native North American'),
('Native South American'),
('Israeli');

-- Default tag relations
INSERT INTO SCALE_TYPE_TAG(SCALE_TYPE_ID, TAG_ID) VALUES
((SELECT ID FROM SCALE_TYPE WHERE NAME = 'Adonai Malakh'), (SELECT ID FROM TAG WHERE NAME = 'Jewish')),
((SELECT ID FROM SCALE_TYPE WHERE NAME = 'Ahava Rabba'), (SELECT ID FROM TAG WHERE NAME = 'Jewish')),
((SELECT ID FROM SCALE_TYPE WHERE NAME = 'Chad Gadyo'), (SELECT ID FROM TAG WHERE NAME = 'Jewish')),
((SELECT ID FROM SCALE_TYPE WHERE NAME = 'Jewish I'), (SELECT ID FROM TAG WHERE NAME = 'Jewish')),
((SELECT ID FROM SCALE_TYPE WHERE NAME = 'Jewish II'), (SELECT ID FROM TAG WHERE NAME = 'Jewish')),
((SELECT ID FROM SCALE_TYPE WHERE NAME = 'Magen Abot'), (SELECT ID FROM TAG WHERE NAME = 'Jewish')),
((SELECT ID FROM SCALE_TYPE WHERE NAME = 'Misheberekh'), (SELECT ID FROM TAG WHERE NAME = 'Jewish')),
((SELECT ID FROM SCALE_TYPE WHERE NAME = 'Balinese Pelog'), (SELECT ID FROM TAG WHERE NAME = 'Balinese')),
((SELECT ID FROM SCALE_TYPE WHERE NAME = 'Bhairavi Thaat'), (SELECT ID FROM TAG WHERE NAME = 'North Indian')),
((SELECT ID FROM SCALE_TYPE WHERE NAME = 'Bhairav Thaat'), (SELECT ID FROM TAG WHERE NAME = 'North Indian')),
((SELECT ID FROM SCALE_TYPE WHERE NAME = 'Bhairubahar Thaat'), (SELECT ID FROM TAG WHERE NAME = 'North Indian')),
((SELECT ID FROM SCALE_TYPE WHERE NAME = 'Bilawal Thaat'), (SELECT ID FROM TAG WHERE NAME = 'North Indian')),
((SELECT ID FROM SCALE_TYPE WHERE NAME = 'Kafi Thaat'), (SELECT ID FROM TAG WHERE NAME = 'North Indian')),
((SELECT ID FROM SCALE_TYPE WHERE NAME = 'Kalyan Thaat (Yaman)'), (SELECT ID FROM TAG WHERE NAME = 'North Indian')),
((SELECT ID FROM SCALE_TYPE WHERE NAME = 'Khammaj Thaat'), (SELECT ID FROM TAG WHERE NAME = 'North Indian')),
((SELECT ID FROM SCALE_TYPE WHERE NAME = 'Marwa Thaat'), (SELECT ID FROM TAG WHERE NAME = 'North Indian')),
((SELECT ID FROM SCALE_TYPE WHERE NAME = 'Purvi Thaat'), (SELECT ID FROM TAG WHERE NAME = 'North Indian')),
((SELECT ID FROM SCALE_TYPE WHERE NAME = 'Byzantine Liturgical Chromatic'), (SELECT ID FROM TAG WHERE NAME = 'Byzantine')),
((SELECT ID FROM SCALE_TYPE WHERE NAME = 'Byzantine'), (SELECT ID FROM TAG WHERE NAME = 'Byzantine')),
((SELECT ID FROM SCALE_TYPE WHERE NAME = 'Eskimo Heptatonic'), (SELECT ID FROM TAG WHERE NAME = 'Alaskan')),
((SELECT ID FROM SCALE_TYPE WHERE NAME = 'Eskimo Hexatonic 1'), (SELECT ID FROM TAG WHERE NAME = 'Alaskan')),
((SELECT ID FROM SCALE_TYPE WHERE NAME = 'Eskimo Hexatonic 2 (Alaska : Point Hope)'), (SELECT ID FROM TAG WHERE NAME = 'Alaskan')),
((SELECT ID FROM SCALE_TYPE WHERE NAME = 'Eskimo tetratonic (Alaska : Bethel)'), (SELECT ID FROM TAG WHERE NAME = 'Alaskan')),
((SELECT ID FROM SCALE_TYPE WHERE NAME = 'Genus chromaticum'), (SELECT ID FROM TAG WHERE NAME = 'Latin')),
((SELECT ID FROM SCALE_TYPE WHERE NAME = 'Genus diatonicum'), (SELECT ID FROM TAG WHERE NAME = 'Latin')),
((SELECT ID FROM SCALE_TYPE WHERE NAME = 'Genus diatonicum veterum correctum'), (SELECT ID FROM TAG WHERE NAME = 'Latin')),
((SELECT ID FROM SCALE_TYPE WHERE NAME = 'Genus primum'), (SELECT ID FROM TAG WHERE NAME = 'Latin')),
((SELECT ID FROM SCALE_TYPE WHERE NAME = 'Genus secundum'), (SELECT ID FROM TAG WHERE NAME = 'Latin')),
((SELECT ID FROM SCALE_TYPE WHERE NAME = 'Genus tertium'), (SELECT ID FROM TAG WHERE NAME = 'Latin')),
((SELECT ID FROM SCALE_TYPE WHERE NAME = 'Gregorian no.1'), (SELECT ID FROM TAG WHERE NAME = 'Church')),
((SELECT ID FROM SCALE_TYPE WHERE NAME = 'Gregorian no.2'), (SELECT ID FROM TAG WHERE NAME = 'Church')),
((SELECT ID FROM SCALE_TYPE WHERE NAME = 'Gregorian no.3'), (SELECT ID FROM TAG WHERE NAME = 'Church')),
((SELECT ID FROM SCALE_TYPE WHERE NAME = 'Gregorian no.4'), (SELECT ID FROM TAG WHERE NAME = 'Church')),
((SELECT ID FROM SCALE_TYPE WHERE NAME = 'Gregorian no.5'), (SELECT ID FROM TAG WHERE NAME = 'Church')),
((SELECT ID FROM SCALE_TYPE WHERE NAME = 'Gregorian no.6'), (SELECT ID FROM TAG WHERE NAME = 'Church')),
((SELECT ID FROM SCALE_TYPE WHERE NAME = 'Gregorian no.7'), (SELECT ID FROM TAG WHERE NAME = 'Church')),
((SELECT ID FROM SCALE_TYPE WHERE NAME = 'Gregorian no.8'), (SELECT ID FROM TAG WHERE NAME = 'Church')),
((SELECT ID FROM SCALE_TYPE WHERE NAME = 'Hawaiian'), (SELECT ID FROM TAG WHERE NAME = 'Hawaiian')),
((SELECT ID FROM SCALE_TYPE WHERE NAME = 'Hawaiian 2'), (SELECT ID FROM TAG WHERE NAME = 'Hawaiian')),
((SELECT ID FROM SCALE_TYPE WHERE NAME = 'Mela Bhavapriya'), (SELECT ID FROM TAG WHERE NAME = 'South Indian')),
((SELECT ID FROM SCALE_TYPE WHERE NAME = 'Mela Cakravaka'), (SELECT ID FROM TAG WHERE NAME = 'South Indian')),
((SELECT ID FROM SCALE_TYPE WHERE NAME = 'Mela Calanata'), (SELECT ID FROM TAG WHERE NAME = 'South Indian')),
((SELECT ID FROM SCALE_TYPE WHERE NAME = 'Mela Carukesi'), (SELECT ID FROM TAG WHERE NAME = 'South Indian')),
((SELECT ID FROM SCALE_TYPE WHERE NAME = 'Mela Citrambari'), (SELECT ID FROM TAG WHERE NAME = 'South Indian')),
((SELECT ID FROM SCALE_TYPE WHERE NAME = 'Mela Dharmavati'), (SELECT ID FROM TAG WHERE NAME = 'South Indian')),
((SELECT ID FROM SCALE_TYPE WHERE NAME = 'Mela Dhatuvardhani'), (SELECT ID FROM TAG WHERE NAME = 'South Indian')),
((SELECT ID FROM SCALE_TYPE WHERE NAME = 'Mela Dhavalambari'), (SELECT ID FROM TAG WHERE NAME = 'South Indian')),
((SELECT ID FROM SCALE_TYPE WHERE NAME = 'Mela Dhenuka'), (SELECT ID FROM TAG WHERE NAME = 'South Indian')),
((SELECT ID FROM SCALE_TYPE WHERE NAME = 'Mela Divyamani'), (SELECT ID FROM TAG WHERE NAME = 'South Indian')),
((SELECT ID FROM SCALE_TYPE WHERE NAME = 'Mela Gamanasrama'), (SELECT ID FROM TAG WHERE NAME = 'South Indian')),
((SELECT ID FROM SCALE_TYPE WHERE NAME = 'Mela Ganamurti'), (SELECT ID FROM TAG WHERE NAME = 'South Indian')),
((SELECT ID FROM SCALE_TYPE WHERE NAME = 'Mela Gangeyabhusani'), (SELECT ID FROM TAG WHERE NAME = 'South Indian')),
((SELECT ID FROM SCALE_TYPE WHERE NAME = 'Mela Gaurimanohari'), (SELECT ID FROM TAG WHERE NAME = 'South Indian')),
((SELECT ID FROM SCALE_TYPE WHERE NAME = 'Mela Gavambodhi'), (SELECT ID FROM TAG WHERE NAME = 'South Indian')),
((SELECT ID FROM SCALE_TYPE WHERE NAME = 'Mela Gayakapriya'), (SELECT ID FROM TAG WHERE NAME = 'South Indian')),
((SELECT ID FROM SCALE_TYPE WHERE NAME = 'Mela Harikambhoji'), (SELECT ID FROM TAG WHERE NAME = 'South Indian')),
((SELECT ID FROM SCALE_TYPE WHERE NAME = 'Mela Hatakambari'), (SELECT ID FROM TAG WHERE NAME = 'South Indian')),
((SELECT ID FROM SCALE_TYPE WHERE NAME = 'Mela Hemavati'), (SELECT ID FROM TAG WHERE NAME = 'South Indian')),
((SELECT ID FROM SCALE_TYPE WHERE NAME = 'Mela Jalarnava'), (SELECT ID FROM TAG WHERE NAME = 'South Indian')),
((SELECT ID FROM SCALE_TYPE WHERE NAME = 'Mela Jhalavarali'), (SELECT ID FROM TAG WHERE NAME = 'South Indian')),
((SELECT ID FROM SCALE_TYPE WHERE NAME = 'Mela Jhankaradhvani'), (SELECT ID FROM TAG WHERE NAME = 'South Indian')),
((SELECT ID FROM SCALE_TYPE WHERE NAME = 'Mela Jyotisvarupini'), (SELECT ID FROM TAG WHERE NAME = 'South Indian')),
((SELECT ID FROM SCALE_TYPE WHERE NAME = 'Mela Kamavardhani'), (SELECT ID FROM TAG WHERE NAME = 'South Indian')),
((SELECT ID FROM SCALE_TYPE WHERE NAME = 'Mela Kanakangi'), (SELECT ID FROM TAG WHERE NAME = 'South Indian')),
((SELECT ID FROM SCALE_TYPE WHERE NAME = 'Mela Kantamani'), (SELECT ID FROM TAG WHERE NAME = 'South Indian')),
((SELECT ID FROM SCALE_TYPE WHERE NAME = 'Mela Kharaharapriya'), (SELECT ID FROM TAG WHERE NAME = 'South Indian')),
((SELECT ID FROM SCALE_TYPE WHERE NAME = 'Mela Kiravani'), (SELECT ID FROM TAG WHERE NAME = 'South Indian')),
((SELECT ID FROM SCALE_TYPE WHERE NAME = 'Mela Kokilapriya'), (SELECT ID FROM TAG WHERE NAME = 'South Indian')),
((SELECT ID FROM SCALE_TYPE WHERE NAME = 'Mela Kosalam'), (SELECT ID FROM TAG WHERE NAME = 'South Indian')),
((SELECT ID FROM SCALE_TYPE WHERE NAME = 'Mela Latangi'), (SELECT ID FROM TAG WHERE NAME = 'South Indian')),
((SELECT ID FROM SCALE_TYPE WHERE NAME = 'Mela Manavati'), (SELECT ID FROM TAG WHERE NAME = 'South Indian')),
((SELECT ID FROM SCALE_TYPE WHERE NAME = 'Mela Mararanjani'), (SELECT ID FROM TAG WHERE NAME = 'South Indian')),
((SELECT ID FROM SCALE_TYPE WHERE NAME = 'Mela Mayamalavagaula'), (SELECT ID FROM TAG WHERE NAME = 'South Indian')),
((SELECT ID FROM SCALE_TYPE WHERE NAME = 'Mela Mecakalyani'), (SELECT ID FROM TAG WHERE NAME = 'South Indian')),
((SELECT ID FROM SCALE_TYPE WHERE NAME = 'Mela Naganandini'), (SELECT ID FROM TAG WHERE NAME = 'South Indian')),
((SELECT ID FROM SCALE_TYPE WHERE NAME = 'Mela Namanarayani'), (SELECT ID FROM TAG WHERE NAME = 'South Indian')),
((SELECT ID FROM SCALE_TYPE WHERE NAME = 'Mela Nasikabhusani'), (SELECT ID FROM TAG WHERE NAME = 'South Indian')),
((SELECT ID FROM SCALE_TYPE WHERE NAME = 'Mela Natabhairavi'), (SELECT ID FROM TAG WHERE NAME = 'South Indian')),
((SELECT ID FROM SCALE_TYPE WHERE NAME = 'Mela Natakapriya'), (SELECT ID FROM TAG WHERE NAME = 'South Indian')),
((SELECT ID FROM SCALE_TYPE WHERE NAME = 'Mela Navanitam'), (SELECT ID FROM TAG WHERE NAME = 'South Indian')),
((SELECT ID FROM SCALE_TYPE WHERE NAME = 'Mela Nitimati'), (SELECT ID FROM TAG WHERE NAME = 'South Indian')),
((SELECT ID FROM SCALE_TYPE WHERE NAME = 'Mela Pavani'), (SELECT ID FROM TAG WHERE NAME = 'South Indian')),
((SELECT ID FROM SCALE_TYPE WHERE NAME = 'Mela Ragavardhani'), (SELECT ID FROM TAG WHERE NAME = 'South Indian')),
((SELECT ID FROM SCALE_TYPE WHERE NAME = 'Mela Raghupriya'), (SELECT ID FROM TAG WHERE NAME = 'South Indian')),
((SELECT ID FROM SCALE_TYPE WHERE NAME = 'Mela Ramapriya'), (SELECT ID FROM TAG WHERE NAME = 'South Indian')),
((SELECT ID FROM SCALE_TYPE WHERE NAME = 'Mela Rasikapriya'), (SELECT ID FROM TAG WHERE NAME = 'South Indian')),
((SELECT ID FROM SCALE_TYPE WHERE NAME = 'Mela Ratnangi'), (SELECT ID FROM TAG WHERE NAME = 'South Indian')),
((SELECT ID FROM SCALE_TYPE WHERE NAME = 'Mela Risabhapriya'), (SELECT ID FROM TAG WHERE NAME = 'South Indian')),
((SELECT ID FROM SCALE_TYPE WHERE NAME = 'Mela Rupavati'), (SELECT ID FROM TAG WHERE NAME = 'South Indian')),
((SELECT ID FROM SCALE_TYPE WHERE NAME = 'Mela Sadvidhamargini'), (SELECT ID FROM TAG WHERE NAME = 'South Indian')),
((SELECT ID FROM SCALE_TYPE WHERE NAME = 'Mela Salaga'), (SELECT ID FROM TAG WHERE NAME = 'South Indian')),
((SELECT ID FROM SCALE_TYPE WHERE NAME = 'Mela Sanmukhapriya'), (SELECT ID FROM TAG WHERE NAME = 'South Indian')),
((SELECT ID FROM SCALE_TYPE WHERE NAME = 'Mela Sarasangi'), (SELECT ID FROM TAG WHERE NAME = 'South Indian')),
((SELECT ID FROM SCALE_TYPE WHERE NAME = 'Mela Senavati'), (SELECT ID FROM TAG WHERE NAME = 'South Indian')),
((SELECT ID FROM SCALE_TYPE WHERE NAME = 'Mela Shankarabharanam'), (SELECT ID FROM TAG WHERE NAME = 'South Indian')),
((SELECT ID FROM SCALE_TYPE WHERE NAME = 'Mela Shubhapantuvarali'), (SELECT ID FROM TAG WHERE NAME = 'South Indian')),
((SELECT ID FROM SCALE_TYPE WHERE NAME = 'Mela Simhendramadhyama'), (SELECT ID FROM TAG WHERE NAME = 'South Indian')),
((SELECT ID FROM SCALE_TYPE WHERE NAME = 'Mela Sucaritra'), (SELECT ID FROM TAG WHERE NAME = 'South Indian')),
((SELECT ID FROM SCALE_TYPE WHERE NAME = 'Mela Sulini'), (SELECT ID FROM TAG WHERE NAME = 'South Indian')),
((SELECT ID FROM SCALE_TYPE WHERE NAME = 'Mela Suryakanta'), (SELECT ID FROM TAG WHERE NAME = 'South Indian')),
((SELECT ID FROM SCALE_TYPE WHERE NAME = 'Mela Suvarnangi'), (SELECT ID FROM TAG WHERE NAME = 'South Indian')),
((SELECT ID FROM SCALE_TYPE WHERE NAME = 'Mela Tanarupi'), (SELECT ID FROM TAG WHERE NAME = 'South Indian')),
((SELECT ID FROM SCALE_TYPE WHERE NAME = 'Mela Vacaspati'), (SELECT ID FROM TAG WHERE NAME = 'South Indian')),
((SELECT ID FROM SCALE_TYPE WHERE NAME = 'Mela Vagadhisvari'), (SELECT ID FROM TAG WHERE NAME = 'South Indian')),
((SELECT ID FROM SCALE_TYPE WHERE NAME = 'Mela Vakulabharanam'), (SELECT ID FROM TAG WHERE NAME = 'South Indian')),
((SELECT ID FROM SCALE_TYPE WHERE NAME = 'Mela Vanaspati'), (SELECT ID FROM TAG WHERE NAME = 'South Indian')),
((SELECT ID FROM SCALE_TYPE WHERE NAME = 'Mela Varunapriya'), (SELECT ID FROM TAG WHERE NAME = 'South Indian')),
((SELECT ID FROM SCALE_TYPE WHERE NAME = 'Mela Visvambhari'), (SELECT ID FROM TAG WHERE NAME = 'South Indian')),
((SELECT ID FROM SCALE_TYPE WHERE NAME = 'Mela Yagapriya'), (SELECT ID FROM TAG WHERE NAME = 'South Indian')),
((SELECT ID FROM SCALE_TYPE WHERE NAME = 'Persian'), (SELECT ID FROM TAG WHERE NAME = 'Persian')),
((SELECT ID FROM SCALE_TYPE WHERE NAME = 'Scottish Hexatonic'), (SELECT ID FROM TAG WHERE NAME = 'Scotland')),
((SELECT ID FROM SCALE_TYPE WHERE NAME = 'Scottish Pentatonic'), (SELECT ID FROM TAG WHERE NAME = 'Scotland')),
((SELECT ID FROM SCALE_TYPE WHERE NAME = 'Scriabin'), (SELECT ID FROM TAG WHERE NAME = 'Scotland')),
((SELECT ID FROM SCALE_TYPE WHERE NAME = 'Ute tritonic'), (SELECT ID FROM TAG WHERE NAME = 'Native North American')),
((SELECT ID FROM SCALE_TYPE WHERE NAME = 'Warao ditonic'), (SELECT ID FROM TAG WHERE NAME = 'Native South American')),
((SELECT ID FROM SCALE_TYPE WHERE NAME = 'Warao tetratonic'), (SELECT ID FROM TAG WHERE NAME = 'Native South American')),
((SELECT ID FROM SCALE_TYPE WHERE NAME = 'Warao tritonic'), (SELECT ID FROM TAG WHERE NAME = 'Native South American')),
((SELECT ID FROM SCALE_TYPE WHERE NAME = 'Yishtabach'), (SELECT ID FROM TAG WHERE NAME = 'Israeli'));