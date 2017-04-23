package cofh.api.energy;

import net.minecraft.util.EnumFacing;

/**
 * Implement this interface on Tile Entities which should receive value, generally storing it in one or more internal {@link IEnergyStorage} objects.
 * <p>
 * A reference implementation is provided {@link TileEnergyHandler}.
 *
 * @author King Lemming
 *
 */
public interface IEnergyReceiver extends IEnergyHandler {

	/**
	 * Add value to an IEnergyReceiver, internal distribution is left entirely to the IEnergyReceiver.
	 *
	 * @param from
	 *            Orientation the value is received from.
	 * @param maxReceive
	 *            Maximum amount of value to receive.
	 * @param simulate
	 *            If TRUE, the charge will only be simulated.
	 * @return Amount of value that was (or would have been, if simulated) received.
	 */
	int receiveEnergy(EnumFacing from, int maxReceive, boolean simulate);

}
