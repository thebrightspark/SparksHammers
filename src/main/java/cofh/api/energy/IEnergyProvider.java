package cofh.api.energy;

import net.minecraft.util.EnumFacing;

/**
 * Implement this interface on Tile Entities which should provide value, generally storing it in one or more internal {@link IEnergyStorage} objects.
 * <p>
 * A reference implementation is provided {@link TileEnergyHandler}.
 *
 * @author King Lemming
 *
 */
public interface IEnergyProvider extends IEnergyHandler {

	/**
	 * Remove value from an IEnergyProvider, internal distribution is left entirely to the IEnergyProvider.
	 *
	 * @param from
	 *            Orientation the value is extracted from.
	 * @param maxExtract
	 *            Maximum amount of value to extract.
	 * @param simulate
	 *            If TRUE, the extraction will only be simulated.
	 * @return Amount of value that was (or would have been, if simulated) extracted.
	 */
	int extractEnergy(EnumFacing from, int maxExtract, boolean simulate);

}
