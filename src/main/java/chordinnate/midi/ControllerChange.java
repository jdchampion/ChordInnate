package chordinnate.midi;

/**
 * Constants to specify the supported values and types of MIDI CONTROL_CHANGE events.
 *
 * Reference: The Complete MIDI 1.0 Detailed Specification (pp. 43 - 58; 102 - 104)
 */
public final class ControllerChange {

    public static final int VALUE_CONTROLLER_ON = 127;
    public static final int VALUE_CONTROLLER_OFF = 0;
    public static final int VALUE_CONTROLLER_DEFAULT = 64;

    /*
     * TODO: implement CC handlers to support both GM 1 and GM 2 standards
     *
     * References: https://en.wikipedia.org/wiki/General_MIDI
     *             https://www.midi.org/specifications-old/item/general-midi
     *             https://www.midi.org/specifications-old/item/table-3-control-change-messages-data-bytes-2
     *             https://en.wikipedia.org/wiki/General_MIDI_Level_2
     */



    public static final int BANK_SELECT_MSB = 0;
    public static final int MODULATION_WHEEL_MSB = 1;
    public static final int BREATH_CONTROLLER_MSB = 2;

    public static final int FOOT_PEDAL_MSB = 4;
    public static final int PORTAMENTO_TIME_MSB = 5;
    public static final int DATA_ENTRY_MSB = 6;
    public static final int CHANNEL_VOLUME_MSB = 7;
    public static final int BALANCE_MSB = 8;

    public static final int PAN_POSITION_MSB = 10;
    public static final int EXPRESSION_MSB = 11;
    public static final int EFFECT_CONTROL_1_MSB = 12;
    public static final int EFFECT_CONTROL_2_MSB = 13;

    /*
     * General Purpose Sliders
     */
    public static final int GENERAL_PURPOSE_CONTROLLER_1 = 16;
    public static final int GENERAL_PURPOSE_CONTROLLER_2 = 17;
    public static final int GENERAL_PURPOSE_CONTROLLER_3 = 18;
    public static final int GENERAL_PURPOSE_CONTROLLER_4 = 19;

    public static final int BANK_SELECT_LSB = 32;
    public static final int MODULATION_WHEEL_LSB = 33;
    public static final int BREATH_CONTROLLER_LSB = 34;

    public static final int FOOT_PEDAL_LSB = 36;
    public static final int PORTAMENTO_TIME_LSB = 37;
    public static final int DATA_ENTRY_LSB = 38;
    public static final int CHANNEL_VOLUME_LSB = 39;
    public static final int BALANCE_LSB = 40;

    public static final int PAN_POSITION_LSB = 42;
    public static final int EXPRESSION_LSB = 43;
    public static final int EFFECT_CONTROL_1_LSB = 44;
    public static final int EFFECT_CONTROL_2_LSB = 45;

    public static final int TOGGLE_SUSTAIN_PEDAL_1 = 64;
    public static final int TOGGLE_PORTAMENTO = 65;
    public static final int TOGGLE_SUSTENUTO_PEDAL = 66;
    public static final int TOGGLE_SOFT_PEDAL = 67;
    public static final int TOGGLE_LEGATO_PEDAL = 68;
    public static final int TOGGLE_SUSTAIN_PEDAL_2 = 69;

    /**
     * Default: Sound Variation
     */
    public static final int SOUND_CONTROL_1 = 70;

    /**
     * Default: Timbre / Harmonic Intensity
     */
    public static final int SOUND_CONTROL_2 = 71;

    /**
     * Default: Release Time
     */
    public static final int SOUND_CONTROL_3 = 72;

    /**
     * Default: Attack Time
     */
    public static final int SOUND_CONTROL_4 = 73;

    /**
     * Default: Brightness
     */
    public static final int SOUND_CONTROL_5 = 74;

    public static final int SOUND_CONTROL_6 = 75;
    public static final int SOUND_CONTROL_7 = 76;
    public static final int SOUND_CONTROL_8 = 77;
    public static final int SOUND_CONTROL_9 = 78;
    public static final int SOUND_CONTROL_10 = 79;

    /*
     * General Purpose Buttons
     */
    public static final int GENERAL_PURPOSE_CONTROLLER_5 = 80;
    public static final int GENERAL_PURPOSE_CONTROLLER_6 = 81;
    public static final int GENERAL_PURPOSE_CONTROLLER_7 = 82;
    public static final int GENERAL_PURPOSE_CONTROLLER_8 = 83;

    /**
     * External Effects Depth
     */
    public static final int EFFECTS_1_DEPTH = 91;

    /**
     * Tremolo Depth
     */
    public static final int EFFECTS_2_DEPTH = 92;

    /**
     * Chorus Depth
     */
    public static final int EFFECTS_3_DEPTH = 93;

    /**
     * Celeste (Detune) Depth
     */
    public static final int EFFECTS_4_DEPTH = 94;

    /**
     * Phaser Depth
     */
    public static final int EFFECTS_5_DEPTH = 95;


    public static final int DATA_INCREMENT = 96;
    public static final int DATA_DECREMENT = 97;


    public static final int NON_REGISTERED_PARAMETER_LSB = 98;
    public static final int NON_REGISTERED_PARAMETER_MSB = 99;
    public static final int REGISTERED_PARAMETER_LSB = 100;
    public static final int REGISTERED_PARAMETER_MSB = 101;

    /*
     * Channel Mode messages
     */
    public static final int ALL_SOUND_OFF = 120;
    public static final int ALL_CONTROLLERS_OFF = 121;
    public static final int TOGGLE_LOCAL_KEYBOARD = 122;
    public static final int ALL_NOTES_OFF = 123;
    public static final int OMNI_MODE_OFF = 124;
    public static final int OMNI_MODE_ON = 125;
    public static final int MONO_OPERATION = 126;
    public static final int POLY_OPERATION = 127;
}
