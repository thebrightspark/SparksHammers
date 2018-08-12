package brightspark.sparkshammers.item.upgrade;

public enum DigSize
{
    THREE(1),
    FIVE(2),
    SEVEN(3);

    private final String name;
    private final int size;

    DigSize(int size)
    {
        int width = size * 2 + 1;
        this.name = width + "x" + width;
        this.size = size;
    }

    public DigSize nextSize(Upgrade sizeUpgrade)
    {
        int i = ordinal() >= Math.min(values().length - 1, sizeUpgrade.getNum()) ? 0 : ordinal() + 1;
        return values()[i];
    }

    public int getSize()
    {
        return size;
    }

    @Override
    public String toString()
    {
        return name;
    }
}
