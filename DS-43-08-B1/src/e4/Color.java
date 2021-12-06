package e4;

public enum Color {
    AMBER {
        @Override
        public Color next() {
            return RED;
        }
    },
    RED {
        @Override
        public Color next() {
            return GREEN;
        }
    },
    GREEN {
        @Override
        public Color next() {
            return AMBER;
        }
    };

    public abstract Color next();
}
