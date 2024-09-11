#include <SFML/Graphics.hpp>
using namespace sf;

#include <iostream>
using namespace std;

#include <fstream>
#include<vector>
#include<queue>
#include<stack>

struct cell{
    int row;
    int col;
};

const int dr[] = {-1, 1, 0, 0};
const int dc[] = {0, 0, -1, 1};

vector< vector<int> > checkYellow(int row, int col, vector< vector<int> > colorGrid, cell start, cell end){

    bool check = 1;
    while(check !=0){
        check = 0;
        for(int r=0; r<row; r++){
            for(int c=0; c<col; c++){
                //std::cout << colorGrid[r][c] << " ";
                if(colorGrid[r][c]==1){
                    if(r!=start.row || c!=start.col){
                        if(r!=end.row || c!= end.col){
                            cell newCell;
                            newCell.row = r;
                            newCell.col = c;

                            int checkYellow = 0;

                            for(int i=0; i<4; i++){
                                int newRow = -1;
                                int newCol = -1;

                                if(newCell.row+dr[i] >=0 && newCell.row+dr[i] < row){
                                    newRow = newCell.row + dr[i];
                                }
                                if(newCell.col+dc[i] >=0 && newCell.col+dc[i] < col){
                                    newCol = newCell.col + dc[i];
                                }

                                if(newRow >=0 && newCol >=0){
                                    if(colorGrid[newRow][newCol] == 1){
                                        checkYellow++;
                                    }
                                }

                            }

                            if(checkYellow < 2){
                                colorGrid[newCell.row][newCell.col] = 2;
                                check = 1;
                            }

                        }
                    }

                }
            }
            //std::cout << endl;
        }

    }

    return colorGrid;
}

int main(){

    string mazeName = "";

    if(mazeName == ""){

        cout << "Enter maze file name: " << endl;
        cin >> mazeName;
        
    }

    ifstream mazefile(mazeName);

    if(mazefile.fail()){
        cerr << "file couldn't be opened" << endl;
        return -1;
    }

    int x;
    mazefile >> x;
    int row = x;
    mazefile >> x;
    int col = x;

    //cout << row << endl;
    //cout << col << endl;

    vector< vector<char> > mazeArray(row, vector<char>(col,0));
    char mazeCharacter;

    for(int r=0; r<row; r++){
        for(int c=0; c<col; c++){
            mazefile >> mazeCharacter;
            mazeArray[r][c] = mazeCharacter;
        }
    }

    int whichSort;
    cout << "Type 1 for BFS or 2 for DFS" << endl;
    cin >> whichSort;

    cell start;
    cell end;

    vector<vector<int> > nodeTracker(row, vector<int>(col, 0));

    for(int r=0; r<row; r++){
        for(int c=0; c<col; c++){
            if(mazeArray[r][c] == 'S'){
                nodeTracker[r][c] = 0;
                start.row = r;
                start.col = c;
            }
            if(mazeArray[r][c] == 'E'){
                nodeTracker[r][c] = 0;
                end.row = r;
                end.col = c;
            }
            if(mazeArray[r][c] == '.'){
                nodeTracker[r][c] = 0;
            }
            else if(mazeArray[r][c] == '#'){
                nodeTracker[r][c] = 2;
            }
        }
    }

    queue<cell> q;
    if(whichSort == 1){
        q.push(start);
    }
    stack<cell> s;
    if(whichSort == 2){
        s.push(start);
    }
    nodeTracker[start.row][start.col] = 1;

    vector< vector<int> > DFScolorGrid(row, vector<int>(col, 0));

    bool endfound = 0;

    RenderWindow window( VideoMode(col*15, row*15), "L6C" );
    window.setFramerateLimit(60);

    Event event;

    RectangleShape square;

    while(window.isOpen()){

        window.clear();

        //draw here
        for(int r=0; r<row; r++){
            for(int c=0; c<col; c++){

                square.setSize(Vector2f(15, 15));
                if(mazeArray[r][c] == '.'){
                    square.setFillColor(Color::White);
                    square.setPosition(c*15, r*15);
                }
                else if(mazeArray[r][c] == 'S'){
                    square.setFillColor(Color::Green);
                    square.setPosition(c*15, r*15);
                }
                else if(mazeArray[r][c] == 'E'){
                    square.setFillColor(Color::Red);
                    square.setPosition(c*15, r*15);
                }
                else if(mazeArray[r][c] == '#'){
                    square.setFillColor(Color::Black);
                    square.setPosition(c*15, r*15);
                }
                window.draw(square);
            }
        }

        cell current;
        if(whichSort == 1 && !endfound && !q.empty()){
            current = q.front();
            q.pop();
        }
        else if(whichSort == 2 && !endfound && !s.empty()){
            current = s.top();
            DFScolorGrid[current.row][current.col] = 1;
            s.pop();
        }

        int paths = 0;
        if(!endfound){
            for(int i=0; i<4; i++){
                int newRow;
                int newCol;

                if(current.row+dr[i] >=0 && current.row+dr[i] < row){
                    newRow = current.row + dr[i];
                }
                if(current.col+dc[i] >=0 && current.col+dc[i] < col){
                    newCol = current.col + dc[i];
                }
                
                cell newCell;
                newCell.row = newRow;
                newCell.col = newCol;
                if(nodeTracker[newRow][newCol] == 0){
                    if(whichSort == 1){
                        q.push(newCell);
                    }
                    else if(whichSort == 2){
                        s.push(newCell);
                        DFScolorGrid[newRow][newCol] = 3;
                    }
                    nodeTracker[newRow][newCol] = 1;
                    paths++;
                }

                if(mazeArray[newRow][newCol] == 'E'){
                    endfound = 1;
                    DFScolorGrid[newRow][newCol] = 1;
                    DFScolorGrid = checkYellow(row, col, DFScolorGrid, start, end);
                    break;
                }
            }
        }

        if(paths == 0 && !endfound){
            DFScolorGrid[current.row][current.col] = 2;
        }

        if(s.empty()){
            for(int r=0; r<row; r++){
                for(int c=0; c<col; c++){
                    if(DFScolorGrid[r][c] == 1){
                        DFScolorGrid[r][c] = 2;
                    }
                }
            }
        }

        if(whichSort == 1){
            for(int r=0; r<row; r++){
                for(int c=0; c<col; c++){
                    square.setSize(Vector2f(15, 15));
                    if(nodeTracker[r][c] == 1 && mazeArray[r][c] != 'S' && mazeArray[r][c] !='E'){
                        square.setFillColor(Color::Magenta);
                        square.setPosition(c*15, r*15);
                    }
                    window.draw(square);
                }
            }
        }

        if(whichSort == 2){
            for(int r=0; r<row; r++){
                for(int c=0; c<col; c++){
                    square.setSize(Vector2f(15, 15));
                    if(DFScolorGrid[r][c] == 3 && mazeArray[r][c] != 'S' && mazeArray[r][c] !='E'){
                        square.setFillColor(Color::Blue);
                        square.setPosition(c*15, r*15);
                    }
                    if(DFScolorGrid[r][c] == 1 && (r!=start.row || c!=start.col) && (r!=end.row || c!=end.col)){
                        square.setFillColor(Color::Yellow);
                        square.setPosition(c*15, r*15);
                    }
                    if(DFScolorGrid[r][c] == 2){
                        square.setFillColor(Color::Magenta);
                        square.setPosition(c*15, r*15);
                    }
                    window.draw(square);
                }
            }
        }

        sf::sleep(sf::milliseconds(50));

        window.display();

        //event handling
        while(window.pollEvent(event)){
            if(event.type == Event::Closed){
                window.close();
            }
        }

    }


    return 0;
}