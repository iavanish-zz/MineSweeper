package iavanish.minesweeper.PlayGame;

import android.app.Activity;
import android.os.Bundle;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.view.View;
import android.content.Intent;

import iavanish.minesweeper.R;

public class Level1Game extends Activity implements OnItemClickListener {

    private GridView gridView;
    private ArrayAdapter <String> adapter;

    private StartNewGame newGame;
    private Game game;
    private Lives lives;
    private String[] grid;
    private String[] invisibleGrid;

    private static final int n = 64;
    private static final int noOfMines = 8;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level1_game);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");

        grid = new String[n];
        invisibleGrid = new String[n];
        newGame = new StartNewGame();
        game = new Game();
        lives = new Lives(1);

        newGame.initializeGrid(grid, invisibleGrid, n);
        newGame.initializeInvisibleGrid(grid, invisibleGrid, n, noOfMines);

        gridView = (GridView) findViewById(R.id.gridView1);

        adapter = new ArrayAdapter <String> (this,
                android.R.layout.simple_list_item_activated_1, grid);

        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        if(!grid[position].equals("$")) {
            game.nonMineClickedEvent(grid, invisibleGrid, n, position);
        }

        else if(invisibleGrid[position].equals("M")) {

        }

        else if(invisibleGrid[position].equals("F")) {
            game.flaggedCellClickedEvent(grid, invisibleGrid, n, position);
        }

    }
}
