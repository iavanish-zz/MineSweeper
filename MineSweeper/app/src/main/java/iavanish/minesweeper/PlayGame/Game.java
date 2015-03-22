
package iavanish.minesweeper.PlayGame;


import android.os.Handler;
import android.widget.Button;
import android.app.Activity;
import android.widget.Toast;

import iavanish.minesweeper.EndGame.Loss;
import iavanish.minesweeper.R;


/**
 * Utility class
 */

/**
 * Created by iavanish on 21-Mar-15.
 */


public class Game {

    public Game() {

    }

    public boolean cellClickedEvent(Activity currentActivity, Button[][] cells, ContentOfCell[][] contentOfCells, StatusOfCell[][] statusOfCells,
                                    Grid grid, int noOfRows, int noOfColumns, int noOfMines, Lives lives, int row, int column) {

        if(statusOfCells[row][column] == StatusOfCell.UNCOVERED) {
            return allCellsUncovered(currentActivity, cells, contentOfCells, statusOfCells, grid,
                    noOfRows, noOfColumns, noOfMines, lives, row, column);
        }

        else {

            if(contentOfCells[row][column] == ContentOfCell.NOTHING) {
                return nonMineClickedEvent(currentActivity, cells, contentOfCells, statusOfCells, grid,
                        noOfRows, noOfColumns, noOfMines, lives, row, column);
            }
            else if(contentOfCells[row][column] == ContentOfCell.MINE) {
                return mineCellClickedEvent(currentActivity, cells, contentOfCells, statusOfCells, grid,
                        noOfRows, noOfColumns, noOfMines, lives, row, column);
            }
            else if(contentOfCells[row][column] == ContentOfCell.FLAG) {
                return flaggedCellClickedEvent(currentActivity, cells, contentOfCells, statusOfCells, grid,
                        noOfRows, noOfColumns, noOfMines, lives, row, column);
            }
            else {
                return allCellsUncovered(currentActivity, cells, contentOfCells, statusOfCells, grid,
                        noOfRows, noOfColumns, noOfMines, lives, row, column);
            }

        }

    }

    public boolean nonMineClickedEvent(Activity currentActivity, Button[][] cells, ContentOfCell[][] contentOfCells, StatusOfCell[][] statusOfCells,
                                       Grid grid, int noOfRows, int noOfColumns, int noOfMines, Lives lives, int row, int column) {

        if(statusOfCells[row][column] == StatusOfCell.UNCOVERED) {
            return allCellsUncovered(currentActivity, cells, contentOfCells, statusOfCells, grid,
                    noOfRows, noOfColumns, noOfMines, lives, row, column);
        }

        statusOfCells[row][column] = StatusOfCell.UNCOVERED;
        grid.noOfCoveredCells--;
        grid.noOfUncoveredCells++;

        int neighbours = noOfNeighbouringMines(cells, contentOfCells, statusOfCells, grid,
                noOfRows, noOfColumns, noOfMines, lives, row, column);

        cells[row][column].setText(String.valueOf(neighbours));

        boolean gameOver = allCellsUncovered(currentActivity, cells, contentOfCells, statusOfCells, grid,
                noOfRows, noOfColumns, noOfMines, lives, row, column);
        if(gameOver) {
            return gameOver;
        }

        if(neighbours == 0) {

            if((row-1 >= 0) && (column - 1 >= 0)) {
                gameOver = cellClickedEvent(currentActivity, cells, contentOfCells, statusOfCells, grid,
                        noOfRows, noOfColumns, noOfMines, lives, row-1, column-1);
                if(gameOver) {
                    return gameOver;
                }
            }

            if(row-1 >= 0) {
                gameOver = cellClickedEvent(currentActivity, cells, contentOfCells, statusOfCells, grid,
                        noOfRows, noOfColumns, noOfMines, lives, row-1, column);
                if(gameOver) {
                    return gameOver;
                }
            }

            if((row-1 >= 0) && (column+1 < noOfColumns)) {
                gameOver = cellClickedEvent(currentActivity, cells, contentOfCells, statusOfCells, grid,
                        noOfRows, noOfColumns, noOfMines, lives, row-1, column+1);
                if(gameOver) {
                    return gameOver;
                }
            }

            if(column-1 >= 0) {
                gameOver = cellClickedEvent(currentActivity, cells, contentOfCells, statusOfCells, grid,
                        noOfRows, noOfColumns, noOfMines, lives, row, column-1);
                if(gameOver) {
                    return gameOver;
                }
            }

            if(column+1 < noOfColumns) {
                gameOver = cellClickedEvent(currentActivity, cells, contentOfCells, statusOfCells, grid,
                        noOfRows, noOfColumns, noOfMines, lives, row, column+1);
                if(gameOver) {
                    return gameOver;
                }
            }

            if((row+1 < noOfRows) && (column-1 >= 0)) {
                gameOver = cellClickedEvent(currentActivity, cells, contentOfCells, statusOfCells, grid,
                        noOfRows, noOfColumns, noOfMines, lives, row+1, column-1);
                if(gameOver) {
                    return gameOver;
                }
            }

            if(row+1 < noOfRows) {
                gameOver = cellClickedEvent(currentActivity, cells, contentOfCells, statusOfCells, grid,
                        noOfRows, noOfColumns, noOfMines, lives, row+1, column);
                if(gameOver) {
                    return gameOver;
                }
            }

            if((row+1 < noOfRows) && (column+1 < noOfColumns)) {
                gameOver = cellClickedEvent(currentActivity, cells, contentOfCells, statusOfCells, grid,
                        noOfRows, noOfColumns, noOfMines, lives, row+1, column+1);
                if(gameOver) {
                    return gameOver;
                }
            }

        }

        return allCellsUncovered(currentActivity, cells, contentOfCells, statusOfCells, grid,
                noOfRows, noOfColumns, noOfMines, lives, row, column);

    }

    public boolean mineCellClickedEvent(Activity currentActivity, Button[][] cells, ContentOfCell[][] contentOfCells, StatusOfCell[][] statusOfCells,
                                        Grid grid, int noOfRows, int noOfColumns, int noOfMines, Lives lives, int row, int column) {

        cells[row][column].setBackgroundResource(R.drawable.mine);

        lives.countLives = lives.countLives - 1;
        statusOfCells[row][column] = StatusOfCell.UNCOVERED;
        grid.noOfCoveredCells--;
        grid.noOfUncoveredCells++;

        if(lives.countLives == 0) {

            Toast.makeText(currentActivity, "YOU LOST", Toast.LENGTH_LONG).show();

            revealAllMines(currentActivity, cells, contentOfCells, statusOfCells, grid,
                    noOfRows, noOfColumns, noOfMines, lives, row, column);



            Loss loss = new Loss();
            loss.enterNewGame(currentActivity);
            return false;

        }

        else {
            return allCellsUncovered(currentActivity, cells, contentOfCells, statusOfCells, grid,
                    noOfRows, noOfColumns, noOfMines, lives, row, column);
        }

    }

    public boolean flaggedCellClickedEvent(Activity currentActivity, Button[][] cells, ContentOfCell[][] contentOfCells, StatusOfCell[][] statusOfCells,
                                           Grid grid, int noOfRows, int noOfColumns, int noOfMines, Lives lives, int row, int column) {

        cells[row][column].setBackgroundResource(R.drawable.flag);

        lives.countLives = lives.countLives + 1;
        statusOfCells[row][column] = StatusOfCell.UNCOVERED;
        grid.noOfCoveredCells--;
        grid.noOfUncoveredCells++;

        return allCellsUncovered(currentActivity, cells, contentOfCells, statusOfCells, grid,
                noOfRows, noOfColumns, noOfMines, lives, row, column);

    }

    public boolean allCellsUncovered(Activity currentActivity, Button[][] cells, ContentOfCell[][] contentOfCells, StatusOfCell[][] statusOfCells,
                                     Grid grid, int noOfRows, int noOfColumns, int noOfMines, Lives lives, int row, int column) {

        if(grid.noOfCoveredCells <= noOfMines) {
            revealAllMines(currentActivity, cells, contentOfCells, statusOfCells, grid,
                    noOfRows, noOfColumns, noOfMines, lives, row, column);
            return true;
        }
        else {
            return false;
        }

    }

    private void revealAllMines(Activity currentActivity, Button[][] cells, ContentOfCell[][] contentOfCells, StatusOfCell[][] statusOfCells,
                                Grid grid, int noOfRows, int noOfColumns, int noOfMines, Lives lives, int row, int column) {

        for(int i = 0; i < noOfRows; i++) {
            for(int j = 0; j < noOfColumns; j++) {
                if(statusOfCells[i][j] == StatusOfCell.COVERED) {
                    statusOfCells[i][j] = StatusOfCell.UNCOVERED;
                    if(contentOfCells[i][j] == ContentOfCell.FLAG) {
                        cells[i][j].setBackgroundResource(R.drawable.flag);
                    }
                    else if(contentOfCells[i][j] == ContentOfCell.MINE) {
                        cells[i][j].setBackgroundResource(R.drawable.mine);
                    }
                    else if(contentOfCells[i][j] == ContentOfCell.NOTHING) {
                        //cells[i][j].setText("N");
                    }
                    else {

                    }
                }
                else {

                }
            }
        }

    }

    private int noOfNeighbouringMines(Button[][] cells, ContentOfCell[][] contentOfCells, StatusOfCell[][] statusOfCells,
                                      Grid grid, int noOfRows, int noOfColumns, int noOfMines, Lives lives, int row, int column) {

        int neighbours = 0;

        if((row-1 >= 0) && (column-1 >= 0) && (contentOfCells[row-1][column-1] == ContentOfCell.MINE)) {
            neighbours++;
        }

        if((row-1 >= 0) && (contentOfCells[row-1][column] == ContentOfCell.MINE)) {
            neighbours++;
        }

        if((row-1 >= 0) && (column+1 < noOfColumns) && (contentOfCells[row-1][column+1] == ContentOfCell.MINE)) {
            neighbours++;
        }

        if((column-1 >= 0) && (contentOfCells[row][column-1] == ContentOfCell.MINE)) {
            neighbours++;
        }

        if((column+1 < noOfColumns) && (contentOfCells[row][column+1] == ContentOfCell.MINE)) {
            neighbours++;
        }

        if((row+1 < noOfRows) && (column-1 >= 0) && (contentOfCells[row+1][column-1] == ContentOfCell.MINE)) {
            neighbours++;
        }

        if((row+1 < noOfRows) && (contentOfCells[row+1][column] == ContentOfCell.MINE)) {
            neighbours++;
        }

        if((row+1 < noOfRows) && (column+1 < noOfColumns) && (contentOfCells[row+1][column+1] == ContentOfCell.MINE)) {
            neighbours++;
        }

        return neighbours;

    }

}
