package brightspark.sparkshammers.api;

import brightspark.sparkshammers.customTools.Tool;
import net.minecraftforge.fml.common.eventhandler.Event;

import java.util.Arrays;
import java.util.List;

/**
 * Fired right before items are registered for Sparks Hammers so that other mods can add their own tools to be
 * registered.
 */
public class RegisterToolEvent extends Event
{
    private final List<Tool> toolsList;

    public RegisterToolEvent(List<Tool> toolsList)
    {
        this.toolsList = toolsList;
    }

    public void addTool(ModTool tool)
    {
        toolsList.add(tool);
    }

    public void addTools(ModTool... tools)
    {
        toolsList.addAll(Arrays.asList(tools));
    }
}
