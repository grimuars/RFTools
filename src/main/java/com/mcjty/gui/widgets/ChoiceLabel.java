package com.mcjty.gui.widgets;

import com.mcjty.gui.Window;
import com.mcjty.gui.events.ChoiceEvent;
import com.mcjty.gui.RenderHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;

import java.util.ArrayList;
import java.util.List;

public class ChoiceLabel extends Label<ChoiceLabel> {
    private ArrayList<String> choiceList = new ArrayList<String>();
    private String currentChoice = null;
    private List<ChoiceEvent> choiceEvents = null;

    public ChoiceLabel(Minecraft mc, Gui gui) {
        super(mc, gui);
        setText("");
    }

    public ChoiceLabel addChoices(String ... choices) {
        for (String choice : choices) {
            choiceList.add(choice);
            if (currentChoice == null) {
                currentChoice = choice;
                setText(currentChoice);
                fireChoiceEvents(currentChoice);
            }
        }
        return this;
    }

    public ChoiceLabel setChoice(String choice) {
        currentChoice = choice;
        setText(currentChoice);
        return this;
    }

    public String getCurrentChoice() {
        return currentChoice;
    }

    @Override
    public void draw(Window window, int x, int y) {
        int xx = x + bounds.x;
        int yy = y + bounds.y;

        if (enabled) {
            RenderHelper.drawBeveledBox(xx, yy, xx + bounds.width - 1, yy + bounds.height - 1, 0xffeeeeee, 0xff333333, 0xff666666);
        } else {
            RenderHelper.drawBeveledBox(xx, yy, xx + bounds.width - 1, yy + bounds.height - 1, 0xff888888, 0xff555555, 0xff666666);
        }

        super.draw(window, x, y);
    }

    @Override
    public Widget mouseClick(Window window, int x, int y, int button) {
        if (enabled) {
            int index = choiceList.indexOf(currentChoice);
            index++;
            if (index >= choiceList.size()) {
                index = 0;
            }
            currentChoice = choiceList.get(index);
            setText(currentChoice);
            fireChoiceEvents(currentChoice);
        }
        return null;
    }

    public ChoiceLabel addChoiceEvent(ChoiceEvent event) {
        if (choiceEvents == null) {
            choiceEvents = new ArrayList<ChoiceEvent>();
        }
        choiceEvents.add(event);
        return this;
    }

    public void removeChoiceEvent(ChoiceEvent event) {
        if (choiceEvents != null) {
            choiceEvents.remove(event);
        }
    }

    private void fireChoiceEvents(String choice) {
        if (choiceEvents != null) {
            for (ChoiceEvent event : choiceEvents) {
                event.choiceChanged(this, choice);
            }
        }
    }
}
