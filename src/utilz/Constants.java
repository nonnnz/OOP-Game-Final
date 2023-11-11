package utilz;

public class Constants {
    public static class PlayerConstants{
        public static final int IDEL = 0;
        public static final int ATTACK = 3;
        public static final int JUMP = 5;
        public static final int HIT = 13;

        public static int GetSpriteAmount(int player_action) {
            switch (player_action) {
                case IDEL:
                    return 13;
                case ATTACK:
                    return 10;
                case HIT:
                    return 9;
                case JUMP:
                    return 6;
                default:
                    return 1;
            }
        }

    }
    public static class BatConstants{
        public static final int IDEL = 1;
        public static final int ATTACK = 0;
        public static final int HIT = 2;

        public static int GetSpriteAmount(int bat_action) {
            switch (bat_action) {
                case IDEL:
                    return 5;
                case ATTACK:
                    return 5;
                case HIT:
                    return 5;
                default:
                    return 1;
            }
        }

    }
    public static class MiConstants{
        public static final int IDEL = 0;
        public static final int ATTACK = 1;
        public static final int HIT = 2;

        public static int GetSpriteAmount(int bat_action) {
            switch (bat_action) {
                case IDEL:
                    return 5;
                case ATTACK:
                    return 5;
                case HIT:
                    return 5;
                default:
                    return 1;
            }
        }

    }
}
