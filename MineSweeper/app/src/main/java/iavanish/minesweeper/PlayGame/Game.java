
package iavanish.minesweeper.PlayGame;


import android.widget.Button;
import android.app.Activity;

import iavanish.minesweeper.EndGame.Loss;


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
            return allCellsUncovered(grid, noOfMines);
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
                return allCellsUncovered(grid, noOfMines);
            }

        }

    }

    public boolean nonMineClickedEvent(Activity currentActivity, Button[][] cells, ContentOfCell[][] contentOfCells, StatusOfCell[][] statusOfCells,
                                       Grid grid, int noOfRows, int noOfColumns, int noOfMines, Lives lives, int row, int column) {

        if(statusOfCells[row][column] == StatusOfCell.UNCOVERED) {
            return allCellsUncovered(grid, noOfMines);
        }

        statusOfCells[row][column] = StatusOfCell.UNCOVERED;
        grid.noOfCoveredCells--;
        grid.noOfUncoveredCells++;

        int neighbours = noOfNeighbouringMines(cells, contentOfCells, statusOfCells, grid,
                noOfRows, noOfColumns, noOfMines, lives, row, column);

        cells[row][column].setText(String.valueOf(neighbours));

        boolean gameOver = allCellsUncovered(grid, noOfMines);
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

        return allCellsUncovered(grid, noOfMines);

    }

    public boolean mineCellClickedEvent(Activity currentActivity, Button[][] cells, ContentOfCell[][] contentOfCells, StatusOfCell[][] statusOfCells,
                                        Grid grid, int noOfRows, int noOfColumns, int noOfMines, Lives lives, int row, int column) {

        cells[row][column].setText("M");

        lives.countLives = lives.countLives - 1;
        statusOfCells[row][column] = StatusOfCell.UNCOVERED;
        grid.noOfCoveredCells--;
        grid.noOfUncoveredCells++;

        if(lives.countLives == 0) {
            Loss loss = new Loss();
            loss.enterNewGame(currentActivity);
            return false;
        }
        else {
            return allCellsUncovered(grid, noOfMines);
        }

    }

    public boolean flaggedCellClickedEvent(Activity currentActivity, Button[][] cells, ContentOfCell[][] contentOfCells, StatusOfCell[][] statusOfCells,
                                           Grid grid, int noOfRows, int noOfColumns, int noOfMines, Lives lives, int row, int column) {

        cells[row][column].setText("F");

        lives.countLives = lives.countLives + 1;
        statusOfCells[row][column] = StatusOfCell.UNCOVERED;
        grid.noOfCoveredCells--;
        grid.noOfUncoveredCells++;

        return allCellsUncovered(grid, noOfMines);

    }

    public boolean allCellsUncovered(Grid grid, int noOfMines) {

        if(grid.noOfCoveredCells <= noOfMines) {
            return true;
        }
        else {
            return false;
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
