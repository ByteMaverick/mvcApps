

package mineField;
/*
Edit History:
Akanksha Bodkhe: 3/11 created MoveCommand class to handle player movement in the MineField
model based on a given Heading. The execute() method checks the model type, determines the
movement direction, and calls the move() method on the MineField instance to update the
player's position.
 */

import mvc.*;

    public class MoveCommand extends Command {
        private Heading heading;  // Heading will represent direction (N, S, E, W, etc.)

        public MoveCommand(Model model, Heading heading) {
            super(model);
            this.heading = heading;
        }

        @Override
        public void execute() throws Exception {
            // Ensure that the model is an instance of MineField
            if (!(model instanceof MineField)) {
                throw new IllegalArgumentException("Model is not a valid MineField instance.");
            }

            MineField mineField = (MineField) model;
            int dx = 0, dy = 0;

            // Determine the direction based on the heading
            switch (heading) {
                case N:
                    dy = -1;
                    break;
                case S:
                    dy = 1;
                    break;
                case E:
                    dx = 1;
                    break;
                case W:
                    dx = -1;
                    break;
                case NW:
                    dx = -1;
                    dy = -1;
                    break;
                case NE:
                    dx = 1;
                    dy = -1;
                    break;
                case SW:
                    dx = -1;
                    dy = 1;
                    break;
                case SE:
                    dx = 1;
                    dy = 1;
                    break;
            }

            // Move the player in the determined direction and handle exceptions
            mineField.move(dx, dy);
        }
    }


