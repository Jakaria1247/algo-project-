#include <bits/stdc++.h>
using namespace std;

struct Cell
{
        int row, col;
};

// Function to check if a cell is valid (within the grid boundaries)
bool isValid(int row, int col, int numRows, int numCols)
{
    return (row >= 0 && row < numRows && col >= 0 && col < numCols);
}

// Function to perform DFS to find the treasure
void dfs(vector<vector<int>> &grid, Cell current, Cell treasure, bool &foundTreasure)
{
    if (foundTreasure || !isValid(current.row, current.col, grid.size(), grid[0].size()) || grid[current.row][current.col] == 0)
    {
        // Found the treasure or out of bounds or hit an obstacle, stop exploring
        return;
    }

    if (current.row == treasure.row && current.col == treasure.col)
    {
        // Found the treasure
        foundTreasure = true;
        cout << "Congratulations! You found the treasure.\n";
        return;
    }

    // Mark the current cell as visited
    grid[current.row][current.col] = 0;

    // Explore neighbors (up, down, left, right)
    const int dr[] = {-1, 1, 0, 0};
    const int dc[] = {0, 0, -1, 1};

    for (int i = 0; i < 4; ++i)
    {
        int newRow = current.row + dr[i];
        int newCol = current.col + dc[i];

        dfs(grid, {newRow, newCol}, treasure, foundTreasure);
    }
}

// Function to display the grid
void displayGrid(const vector<vector<int>> &grid)
{
    for (const auto &row : grid)
    {
        for (int cell : row)
        {
            if (cell == 0)
            {
                cout << "# ";
            }
            else
            {
                cout << ". ";
            }
        }
        cout << endl;
    }
}

int main()
{
    {
        // Set the size of the grid
        const int numRows = 5;
        const int numCols = 5;

        // Create the grid with random obstacles
        vector<vector<int>> grid(numRows, vector<int>(numCols, 1));

        // Randomly place obstacles in the grid
        srand(static_cast<unsigned>(time(0)));
        for (int i = 0; i < numRows * numCols / 4; ++i)
        {
            int row = rand() % numRows;
            int col = rand() % numCols;
            grid[row][col] = 0; // 0 represents an obstacle
        }

        // Randomly place the treasure in the grid
        Cell treasure = {rand() % numRows, rand() % numCols};

        // Display the initial grid
        cout << "Welcome to Treasure Hunt!\n";
        cout << "Find the hidden treasure ('.') while avoiding obstacles ('#').\n";
        displayGrid(grid);

        // Get the starting point from the user
        Cell start;
        cout << "Enter the starting point (row col): ";
        cin >> start.row >> start.col;

        // Find the treasure using DFS
        bool foundTreasure = false;
        dfs(grid, start, treasure, foundTreasure);

        if (!foundTreasure)
        {
            cout << "Sorry, you couldn't find the treasure. Better luck next time!\n";
        }
    }
}
