package com.example.chess;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.chess.pieces.Bishop;
import com.example.chess.pieces.Coordinates;
import com.example.chess.pieces.King;
import com.example.chess.pieces.Knight;
import com.example.chess.pieces.Pawn;
import com.example.chess.pieces.Piece;
import com.example.chess.pieces.Position;
import com.example.chess.pieces.Queen;
import com.example.chess.pieces.Rook;

import java.util.ArrayList;
import java.util.Calendar;


public class ChessBoard extends AppCompatActivity implements View.OnClickListener{

    private DatabaseHelper databaseHelper;

    private String whitePlayer;
    private String blackPlayer;


    public Boolean firstPlayerTurn;
    public ArrayList<Coordinates> listOfCoordinates = new ArrayList<>();
    public Position[][] board = new Position[8][8];
    public Position[][] board2 = new Position[8][8];
    public Boolean isFigureSelected = false;
    public Coordinates lastPosition = null ;
    public Coordinates selectedPosition = new Coordinates(0, 0);
    public TextView[][] boardDisplay = new TextView[8][8];
    public TextView[][] BackgroundBoardDisplay = new TextView[8][8];
    public ArrayList<Position[][]> previousMoves = new ArrayList<>();
    public LinearLayout pawnPromotion;
    public int numberOfMoves;

    Piece bKing;
    Piece wKing;

    Piece bQueen;
    Piece wQueen;

    Piece bKnight1;
    Piece bKnight2;
    Piece wKnight1;
    Piece wKnight2;

    Piece bRook1;
    Piece bRook2;
    Piece wRook1;
    Piece wRook2;

    Piece bBishop1;
    Piece bBishop2;
    Piece wBishop1;
    Piece wBishop2;

    Piece bPawn1;
    Piece bPawn2;
    Piece bPawn3;
    Piece bPawn4;
    Piece bPawn5;
    Piece bPawn6;
    Piece bPawn7;
    Piece bPawn8;

    Piece wPawn1;
    Piece wPawn2;
    Piece wPawn3;
    Piece wPawn4;
    Piece wPawn5;
    Piece wPawn6;
    Piece wPawn7;
    Piece wPawn8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.board_chess2);
        databaseHelper = new DatabaseHelper(this);
        Bundle bundle = getIntent().getExtras();

        whitePlayer = bundle.getString(getString(R.string.bundleKeyWhitePlayer));
        blackPlayer = bundle.getString(getString(R.string.bundleKeyBlackPlayer));
        initializeBoard();

        pawnPromotion = findViewById(R.id.pawn_promotion);

        pawnPromotion.setVisibility(View.INVISIBLE);
    }




    public void onSurrenderWhite(View v){
        Intent intent = new Intent(ChessBoard.this, ResultSplash.class);
        boolean isInserted = databaseHelper.insertData(whitePlayer + getString(R.string.versus) + blackPlayer, blackPlayer, Calendar.getInstance().getTime().toString());
        if(isInserted){
            Toast.makeText(ChessBoard.this, (R.string.toastDataSuccessful), Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(ChessBoard.this, getString(R.string.toastDataUnsuccessful), Toast.LENGTH_SHORT).show();

        }
        intent.putExtra(getString(R.string.bundleKeyWinner), blackPlayer);
        startActivity(intent);
    }

    public void onSurrenderBlack(View v){
        Intent intent = new Intent(ChessBoard.this, ResultSplash.class);
        boolean isInserted = databaseHelper.insertData(whitePlayer + getString(R.string.versus) + blackPlayer, whitePlayer, Calendar.getInstance().getTime().toString());
        if(isInserted){
            Toast.makeText(ChessBoard.this, getString(R.string.toastDataSuccessful), Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(ChessBoard.this, getString(R.string.toastDataUnsuccessful), Toast.LENGTH_SHORT).show();

        }
        intent.putExtra(getString(R.string.bundleKeyWinner), whitePlayer);
        startActivity(intent);
    }

    public void onDraw(View v){
        Intent intent = new Intent(ChessBoard.this, ResultSplash.class);
        boolean isInserted = databaseHelper.insertData(whitePlayer + getString(R.string.versus) + blackPlayer, getString(R.string.draw), Calendar.getInstance().getTime().toString());
        if(isInserted){
            Toast.makeText(ChessBoard.this, getString(R.string.toastDataSuccessful), Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(ChessBoard.this, getString(R.string.toastDataUnsuccessful), Toast.LENGTH_SHORT).show();

        }
        intent.putExtra(getString(R.string.bundleKeyWinner), getString(R.string.draw));
        startActivity(intent);
    }

    private void initializeBoard() {
        bKing = new King(false);
        wKing = new King(true);

        bQueen = new Queen(false);
        wQueen = new Queen(true);

        bRook1 = new Rook(false);
        bRook2 = new Rook(false);
        wRook1 = new Rook(true);
        wRook2 = new Rook(true);

        bKnight1 = new Knight(false);
        bKnight2 = new Knight(false);
        wKnight1 = new Knight(true);
        wKnight2 = new Knight(true);

        bBishop1 = new Bishop(false);
        bBishop2 = new Bishop(false);
        wBishop1 = new Bishop(true);
        wBishop2 = new Bishop(true);

        bPawn1 = new Pawn(false);
        bPawn2 = new Pawn(false);
        bPawn3 = new Pawn(false);
        bPawn4 = new Pawn(false);
        bPawn5 = new Pawn(false);
        bPawn6 = new Pawn(false);
        bPawn7 = new Pawn(false);
        bPawn8 = new Pawn(false);

        wPawn1 = new Pawn(true);
        wPawn2 = new Pawn(true);
        wPawn3 = new Pawn(true);
        wPawn4 = new Pawn(true);
        wPawn5 = new Pawn(true);
        wPawn6 = new Pawn(true);
        wPawn7 = new Pawn(true);
        wPawn8 = new Pawn(true);

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = new Position(null);
                board2[i][j] = new Position(null);
            }
        }

        board[0][7].setPiece(wRook1);
        board[1][7].setPiece(wKnight1);
        board[2][7].setPiece(wBishop1);
        board[3][7].setPiece(wQueen);
        board[4][7].setPiece(wKing);
        board[5][7].setPiece(wBishop2);
        board[6][7].setPiece(wKnight2);
        board[7][7].setPiece(wRook2);

        board[0][6].setPiece(wPawn1);
        board[1][6].setPiece(wPawn2);
        board[2][6].setPiece(wPawn3);
        board[3][6].setPiece(wPawn4);
        board[4][6].setPiece(wPawn5);
        board[5][6].setPiece(wPawn6);
        board[6][6].setPiece(wPawn7);
        board[7][6].setPiece(wPawn8);

        board[0][0].setPiece(bRook1);
        board[1][0].setPiece(bKnight1);
        board[2][0].setPiece(bBishop1);
        board[3][0].setPiece(bQueen);
        board[4][0].setPiece(bKing);
        board[5][0].setPiece(bBishop2);
        board[6][0].setPiece(bKnight2);
        board[7][0].setPiece(bRook2);

        board[0][1].setPiece(bPawn1);
        board[1][1].setPiece(bPawn2);
        board[2][1].setPiece(bPawn3);
        board[3][1].setPiece(bPawn4);
        board[4][1].setPiece(bPawn5);
        board[5][1].setPiece(bPawn6);
        board[6][1].setPiece(bPawn7);
        board[7][1].setPiece(bPawn8);

        boardDisplay[0][0] = (TextView) findViewById(R.id.R00);
        BackgroundBoardDisplay[0][0] = (TextView) findViewById(R.id.R000);
        boardDisplay[1][0] = (TextView) findViewById(R.id.R10);
        BackgroundBoardDisplay[1][0] = (TextView) findViewById(R.id.R010);
        boardDisplay[2][0] = (TextView) findViewById(R.id.R20);
        BackgroundBoardDisplay[2][0] = (TextView) findViewById(R.id.R020);
        boardDisplay[3][0] = (TextView) findViewById(R.id.R30);
        BackgroundBoardDisplay[3][0] = (TextView) findViewById(R.id.R030);
        boardDisplay[4][0] = (TextView) findViewById(R.id.R40);
        BackgroundBoardDisplay[4][0] = (TextView) findViewById(R.id.R040);
        boardDisplay[5][0] = (TextView) findViewById(R.id.R50);
        BackgroundBoardDisplay[5][0] = (TextView) findViewById(R.id.R050);
        boardDisplay[6][0] = (TextView) findViewById(R.id.R60);
        BackgroundBoardDisplay[6][0] = (TextView) findViewById(R.id.R060);
        boardDisplay[7][0] = (TextView) findViewById(R.id.R70);
        BackgroundBoardDisplay[7][0] = (TextView) findViewById(R.id.R070);

        boardDisplay[0][1] = (TextView) findViewById(R.id.R01);
        BackgroundBoardDisplay[0][1] = (TextView) findViewById(R.id.R001);
        boardDisplay[1][1] = (TextView) findViewById(R.id.R11);
        BackgroundBoardDisplay[1][1] = (TextView) findViewById(R.id.R011);
        boardDisplay[2][1] = (TextView) findViewById(R.id.R21);
        BackgroundBoardDisplay[2][1] = (TextView) findViewById(R.id.R021);
        boardDisplay[3][1] = (TextView) findViewById(R.id.R31);
        BackgroundBoardDisplay[3][1] = (TextView) findViewById(R.id.R031);
        boardDisplay[4][1] = (TextView) findViewById(R.id.R41);
        BackgroundBoardDisplay[4][1] = (TextView) findViewById(R.id.R041);
        boardDisplay[5][1] = (TextView) findViewById(R.id.R51);
        BackgroundBoardDisplay[5][1] = (TextView) findViewById(R.id.R051);
        boardDisplay[6][1] = (TextView) findViewById(R.id.R61);
        BackgroundBoardDisplay[6][1] = (TextView) findViewById(R.id.R061);
        boardDisplay[7][1] = (TextView) findViewById(R.id.R71);
        BackgroundBoardDisplay[7][1] = (TextView) findViewById(R.id.R071);

        boardDisplay[0][2] = (TextView) findViewById(R.id.R02);
        BackgroundBoardDisplay[0][2] = (TextView) findViewById(R.id.R002);
        boardDisplay[1][2] = (TextView) findViewById(R.id.R12);
        BackgroundBoardDisplay[1][2] = (TextView) findViewById(R.id.R012);
        boardDisplay[2][2] = (TextView) findViewById(R.id.R22);
        BackgroundBoardDisplay[2][2] = (TextView) findViewById(R.id.R022);
        boardDisplay[3][2] = (TextView) findViewById(R.id.R32);
        BackgroundBoardDisplay[3][2] = (TextView) findViewById(R.id.R032);
        boardDisplay[4][2] = (TextView) findViewById(R.id.R42);
        BackgroundBoardDisplay[4][2] = (TextView) findViewById(R.id.R042);
        boardDisplay[5][2] = (TextView) findViewById(R.id.R52);
        BackgroundBoardDisplay[5][2] = (TextView) findViewById(R.id.R052);
        boardDisplay[6][2] = (TextView) findViewById(R.id.R62);
        BackgroundBoardDisplay[6][2] = (TextView) findViewById(R.id.R062);
        boardDisplay[7][2] = (TextView) findViewById(R.id.R72);
        BackgroundBoardDisplay[7][2] = (TextView) findViewById(R.id.R072);

        boardDisplay[0][3] = (TextView) findViewById(R.id.R03);
        BackgroundBoardDisplay[0][3] = (TextView) findViewById(R.id.R003);
        boardDisplay[1][3] = (TextView) findViewById(R.id.R13);
        BackgroundBoardDisplay[1][3] = (TextView) findViewById(R.id.R013);
        boardDisplay[2][3] = (TextView) findViewById(R.id.R23);
        BackgroundBoardDisplay[2][3] = (TextView) findViewById(R.id.R023);
        boardDisplay[3][3] = (TextView) findViewById(R.id.R33);
        BackgroundBoardDisplay[3][3] = (TextView) findViewById(R.id.R033);
        boardDisplay[4][3] = (TextView) findViewById(R.id.R43);
        BackgroundBoardDisplay[4][3] = (TextView) findViewById(R.id.R043);
        boardDisplay[5][3] = (TextView) findViewById(R.id.R53);
        BackgroundBoardDisplay[5][3] = (TextView) findViewById(R.id.R053);
        boardDisplay[6][3] = (TextView) findViewById(R.id.R63);
        BackgroundBoardDisplay[6][3] = (TextView) findViewById(R.id.R063);
        boardDisplay[7][3] = (TextView) findViewById(R.id.R73);
        BackgroundBoardDisplay[7][3] = (TextView) findViewById(R.id.R073);

        boardDisplay[0][4] = (TextView) findViewById(R.id.R04);
        BackgroundBoardDisplay[0][4] = (TextView) findViewById(R.id.R004);
        boardDisplay[1][4] = (TextView) findViewById(R.id.R14);
        BackgroundBoardDisplay[1][4] = (TextView) findViewById(R.id.R014);
        boardDisplay[2][4] = (TextView) findViewById(R.id.R24);
        BackgroundBoardDisplay[2][4] = (TextView) findViewById(R.id.R024);
        boardDisplay[3][4] = (TextView) findViewById(R.id.R34);
        BackgroundBoardDisplay[3][4] = (TextView) findViewById(R.id.R034);
        boardDisplay[4][4] = (TextView) findViewById(R.id.R44);
        BackgroundBoardDisplay[4][4] = (TextView) findViewById(R.id.R044);
        boardDisplay[5][4] = (TextView) findViewById(R.id.R54);
        BackgroundBoardDisplay[5][4] = (TextView) findViewById(R.id.R054);
        boardDisplay[6][4] = (TextView) findViewById(R.id.R64);
        BackgroundBoardDisplay[6][4] = (TextView) findViewById(R.id.R064);
        boardDisplay[7][4] = (TextView) findViewById(R.id.R74);
        BackgroundBoardDisplay[7][4] = (TextView) findViewById(R.id.R074);

        boardDisplay[0][5] = (TextView) findViewById(R.id.R05);
        BackgroundBoardDisplay[0][5] = (TextView) findViewById(R.id.R005);
        boardDisplay[1][5] = (TextView) findViewById(R.id.R15);
        BackgroundBoardDisplay[1][5] = (TextView) findViewById(R.id.R015);
        boardDisplay[2][5] = (TextView) findViewById(R.id.R25);
        BackgroundBoardDisplay[2][5] = (TextView) findViewById(R.id.R025);
        boardDisplay[3][5] = (TextView) findViewById(R.id.R35);
        BackgroundBoardDisplay[3][5] = (TextView) findViewById(R.id.R035);
        boardDisplay[4][5] = (TextView) findViewById(R.id.R45);
        BackgroundBoardDisplay[4][5] = (TextView) findViewById(R.id.R045);
        boardDisplay[5][5] = (TextView) findViewById(R.id.R55);
        BackgroundBoardDisplay[5][5] = (TextView) findViewById(R.id.R055);
        boardDisplay[6][5] = (TextView) findViewById(R.id.R65);
        BackgroundBoardDisplay[6][5] = (TextView) findViewById(R.id.R065);
        boardDisplay[7][5] = (TextView) findViewById(R.id.R75);
        BackgroundBoardDisplay[7][5] = (TextView) findViewById(R.id.R075);

        boardDisplay[0][6] = (TextView) findViewById(R.id.R06);
        BackgroundBoardDisplay[0][6] = (TextView) findViewById(R.id.R006);
        boardDisplay[1][6] = (TextView) findViewById(R.id.R16);
        BackgroundBoardDisplay[1][6] = (TextView) findViewById(R.id.R016);
        boardDisplay[2][6] = (TextView) findViewById(R.id.R26);
        BackgroundBoardDisplay[2][6] = (TextView) findViewById(R.id.R026);
        boardDisplay[3][6] = (TextView) findViewById(R.id.R36);
        BackgroundBoardDisplay[3][6] = (TextView) findViewById(R.id.R036);
        boardDisplay[4][6] = (TextView) findViewById(R.id.R46);
        BackgroundBoardDisplay[4][6] = (TextView) findViewById(R.id.R046);
        boardDisplay[5][6] = (TextView) findViewById(R.id.R56);
        BackgroundBoardDisplay[5][6] = (TextView) findViewById(R.id.R056);
        boardDisplay[6][6] = (TextView) findViewById(R.id.R66);
        BackgroundBoardDisplay[6][6] = (TextView) findViewById(R.id.R066);
        boardDisplay[7][6] = (TextView) findViewById(R.id.R76);
        BackgroundBoardDisplay[7][6] = (TextView) findViewById(R.id.R076);

        boardDisplay[0][7] = (TextView) findViewById(R.id.R07);
        BackgroundBoardDisplay[0][7] = (TextView) findViewById(R.id.R007);
        boardDisplay[1][7] = (TextView) findViewById(R.id.R17);
        BackgroundBoardDisplay[1][7] = (TextView) findViewById(R.id.R017);
        boardDisplay[2][7] = (TextView) findViewById(R.id.R27);
        BackgroundBoardDisplay[2][7] = (TextView) findViewById(R.id.R027);
        boardDisplay[3][7] = (TextView) findViewById(R.id.R37);
        BackgroundBoardDisplay[3][7] = (TextView) findViewById(R.id.R037);
        boardDisplay[4][7] = (TextView) findViewById(R.id.R47);
        BackgroundBoardDisplay[4][7] = (TextView) findViewById(R.id.R047);
        boardDisplay[5][7] = (TextView) findViewById(R.id.R57);
        BackgroundBoardDisplay[5][7] = (TextView) findViewById(R.id.R057);
        boardDisplay[6][7] = (TextView) findViewById(R.id.R67);
        BackgroundBoardDisplay[6][7] = (TextView) findViewById(R.id.R067);
        boardDisplay[7][7] = (TextView) findViewById(R.id.R77);
        BackgroundBoardDisplay[7][7] = (TextView) findViewById(R.id.R077);

        for(int g=0;g<8;g++){
            for(int h=0;h<8;h++){
                if(board[g][h].getPiece()==null){
                    board2[g][h].setPiece(null);
                }else{
                    board2[g][h].setPiece(board[g][h].getPiece());
                }
            }
        }

        numberOfMoves = 0;
        isFigureSelected = false;
        firstPlayerTurn = true;
        setBoard();
    }

    private void setBoard() {

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {

                Piece p = board[i][j].getPiece();
                int x;

                if (board[i][j].getPiece() != null) {
                    if (p instanceof King) x = 0;
                    else if (p instanceof Queen) x = 1;
                    else if (p instanceof Rook) x = 2;
                    else if (p instanceof Bishop) x = 3;
                    else if (p instanceof Knight) x = 4;
                    else if (p instanceof Pawn) x = 5;
                    else x = 6;

                    switch (x) {
                        case 0:
                            if (p.isWhite()) {
                                boardDisplay[i][j].setBackgroundResource(R.drawable.wking);
                            } else {
                                boardDisplay[i][j].setBackgroundResource(R.drawable.bking);
                            }
                            break;

                        case 1:
                            if (p.isWhite()) {
                                boardDisplay[i][j].setBackgroundResource(R.drawable.wqueen);
                            } else {
                                boardDisplay[i][j].setBackgroundResource(R.drawable.bqueen);
                            }
                            break;

                        case 2:
                            if (p.isWhite()) {
                                boardDisplay[i][j].setBackgroundResource(R.drawable.wrook);
                            } else {
                                boardDisplay[i][j].setBackgroundResource(R.drawable.brook);
                            }
                            break;

                        case 3:
                            if (p.isWhite()) {
                                boardDisplay[i][j].setBackgroundResource(R.drawable.wbishop);
                            } else {
                                boardDisplay[i][j].setBackgroundResource(R.drawable.bbishop);
                            }
                            break;

                        case 4:
                            if (p.isWhite()) {
                                boardDisplay[i][j].setBackgroundResource(R.drawable.wknight);
                            } else {
                                boardDisplay[i][j].setBackgroundResource(R.drawable.bknight);
                            }
                            break;

                        case 5:
                            if (p.isWhite()) {
                                boardDisplay[i][j].setBackgroundResource(R.drawable.wpawn);
                            } else {
                                boardDisplay[i][j].setBackgroundResource(R.drawable.bpawn);
                            }
                            break;

                        default:

                    }
                }else{
                    boardDisplay[i][j].setBackgroundResource(0);
                }
            }
        }
        isKingInDanger();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.R00:
                selectedPosition = new Coordinates(0, 0);
                break;
            case R.id.R10:
                selectedPosition.setX(1);
                selectedPosition.setY(0);
                break;
            case R.id.R20:
                selectedPosition.setX(2);
                selectedPosition.setY(0);
                break;
            case R.id.R30:
                selectedPosition.setX(3);
                selectedPosition.setY(0);
                break;
            case R.id.R40:
                selectedPosition.setX(4);
                selectedPosition.setY(0);
                break;
            case R.id.R50:
                selectedPosition.setX(5);
                selectedPosition.setY(0);
                break;
            case R.id.R60:
                selectedPosition.setX(6);
                selectedPosition.setY(0);
                break;
            case R.id.R70:
                selectedPosition.setX(7);
                selectedPosition.setY(0);
                break;

            case R.id.R01:
                selectedPosition.setX(0);
                selectedPosition.setY(1);
                break;
            case R.id.R11:
                selectedPosition.setX(1);
                selectedPosition.setY(1);
                break;
            case R.id.R21:
                selectedPosition.setX(2);
                selectedPosition.setY(1);
                break;
            case R.id.R31:
                selectedPosition.setX(3);
                selectedPosition.setY(1);
                break;
            case R.id.R41:
                selectedPosition.setX(4);
                selectedPosition.setY(1);
                break;
            case R.id.R51:
                selectedPosition.setX(5);
                selectedPosition.setY(1);
                break;
            case R.id.R61:
                selectedPosition.setX(6);
                selectedPosition.setY(1);
                break;
            case R.id.R71:
                selectedPosition.setX(7);
                selectedPosition.setY(1);
                break;

            case R.id.R02:
                selectedPosition.setX(0);
                selectedPosition.setY(2);
                break;
            case R.id.R12:
                selectedPosition.setX(1);
                selectedPosition.setY(2);
                break;
            case R.id.R22:
                selectedPosition.setX(2);
                selectedPosition.setY(2);
                break;
            case R.id.R32:
                selectedPosition.setX(3);
                selectedPosition.setY(2);
                break;
            case R.id.R42:
                selectedPosition.setX(4);
                selectedPosition.setY(2);
                break;
            case R.id.R52:
                selectedPosition.setX(5);
                selectedPosition.setY(2);
                break;
            case R.id.R62:
                selectedPosition.setX(6);
                selectedPosition.setY(2);
                break;
            case R.id.R72:
                selectedPosition.setX(7);
                selectedPosition.setY(2);
                break;

            case R.id.R03:
                selectedPosition.setX(0);
                selectedPosition.setY(3);
                break;
            case R.id.R13:
                selectedPosition.setX(1);
                selectedPosition.setY(3);
                break;
            case R.id.R23:
                selectedPosition.setX(2);
                selectedPosition.setY(3);
                break;
            case R.id.R33:
                selectedPosition.setX(3);
                selectedPosition.setY(3);
                break;
            case R.id.R43:
                selectedPosition.setX(4);
                selectedPosition.setY(3);
                break;
            case R.id.R53:
                selectedPosition.setX(5);
                selectedPosition.setY(3);
                break;
            case R.id.R63:
                selectedPosition.setX(6);
                selectedPosition.setY(3);
                break;
            case R.id.R73:
                selectedPosition.setX(7);
                selectedPosition.setY(3);
                break;

            case R.id.R04:
                selectedPosition.setX(0);
                selectedPosition.setY(4);
                break;
            case R.id.R14:
                selectedPosition.setX(1);
                selectedPosition.setY(4);
                break;
            case R.id.R24:
                selectedPosition.setX(2);
                selectedPosition.setY(4);
                break;
            case R.id.R34:
                selectedPosition.setX(3);
                selectedPosition.setY(4);
                break;
            case R.id.R44:
                selectedPosition.setX(4);
                selectedPosition.setY(4);
                break;
            case R.id.R54:
                selectedPosition.setX(5);
                selectedPosition.setY(4);
                break;
            case R.id.R64:
                selectedPosition.setX(6);
                selectedPosition.setY(4);
                break;
            case R.id.R74:
                selectedPosition.setX(7);
                selectedPosition.setY(4);
                break;

            case R.id.R05:
                selectedPosition.setX(0);
                selectedPosition.setY(5);
                break;
            case R.id.R15:
                selectedPosition.setX(1);
                selectedPosition.setY(5);
                break;
            case R.id.R25:
                selectedPosition.setX(2);
                selectedPosition.setY(5);
                break;
            case R.id.R35:
                selectedPosition.setX(3);
                selectedPosition.setY(5);
                break;
            case R.id.R45:
                selectedPosition.setX(4);
                selectedPosition.setY(5);
                break;
            case R.id.R55:
                selectedPosition.setX(5);
                selectedPosition.setY(5);
                break;
            case R.id.R65:
                selectedPosition.setX(6);
                selectedPosition.setY(5);
                break;
            case R.id.R75:
                selectedPosition.setX(7);
                selectedPosition.setY(5);
                break;

            case R.id.R06:
                selectedPosition.setX(0);
                selectedPosition.setY(6);
                break;
            case R.id.R16:
                selectedPosition.setX(1);
                selectedPosition.setY(6);
                break;
            case R.id.R26:
                selectedPosition.setX(2);
                selectedPosition.setY(6);
                break;
            case R.id.R36:
                selectedPosition.setX(3);
                selectedPosition.setY(6);
                break;
            case R.id.R46:
                selectedPosition.setX(4);
                selectedPosition.setY(6);
                break;
            case R.id.R56:
                selectedPosition.setX(5);
                selectedPosition.setY(6);
                break;
            case R.id.R66:
                selectedPosition.setX(6);
                selectedPosition.setY(6);
                break;
            case R.id.R76:
                selectedPosition.setX(7);
                selectedPosition.setY(6);
                break;

            case R.id.R07:
                selectedPosition.setX(0);
                selectedPosition.setY(7);
                break;
            case R.id.R17:
                selectedPosition.setX(1);
                selectedPosition.setY(7);
                break;
            case R.id.R27:
                selectedPosition.setX(2);
                selectedPosition.setY(7);
                break;
            case R.id.R37:
                selectedPosition.setX(3);
                selectedPosition.setY(7);
                break;
            case R.id.R47:
                selectedPosition.setX(4);
                selectedPosition.setY(7);
                break;
            case R.id.R57:
                selectedPosition.setX(5);
                selectedPosition.setY(7);
                break;
            case R.id.R67:
                selectedPosition.setX(6);
                selectedPosition.setY(7);
                break;
            case R.id.R77:
                selectedPosition.setX(7);
                selectedPosition.setY(7);
                break;
        }

        if (!isFigureSelected) {
            if(board[selectedPosition.getX()][selectedPosition.getY()].getPiece() == null) {
                isKingInDanger();
                return;
            }else{
                if(board[selectedPosition.getX()][selectedPosition.getY()].getPiece().isWhite() != firstPlayerTurn){
                    isKingInDanger();
                    return;
                }else{
                    listOfCoordinates.clear();
                    listOfCoordinates = board[selectedPosition.getX()][selectedPosition.getY()].getPiece().AllowedMoves(selectedPosition, board);
                    BackgroundBoardDisplay[selectedPosition.getX()][selectedPosition.getY()].setBackgroundResource(R.color.colorSelected);
                    setColorAtAllowedPosition(listOfCoordinates);
                    isFigureSelected = true;
                }
            }
        } else {
            if(board[selectedPosition.getX()][selectedPosition.getY()].getPiece() == null){
                if(moveIsAllowed(listOfCoordinates , selectedPosition)){

                    saveBoard();
                    if(board[selectedPosition.getX()][selectedPosition.getY()].getPiece() instanceof King){
                        if(board[selectedPosition.getX()][selectedPosition.getY()].getPiece().isWhite() != firstPlayerTurn){
                            Intent intent = new Intent(ChessBoard.this, ResultSplash.class);
                            boolean isInserted = databaseHelper.insertData(whitePlayer + getString(R.string.versus) + blackPlayer, whitePlayer, Calendar.getInstance().getTime().toString());
                            if(isInserted){
                                Toast.makeText(ChessBoard.this, getString(R.string.toastDataSuccessful), Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(ChessBoard.this, getString(R.string.toastDataUnsuccessful), Toast.LENGTH_SHORT).show();

                            }
                            intent.putExtra(getString(R.string.bundleKeyWinner), whitePlayer);
                            startActivity(intent);
                        }
                    }
                    board[selectedPosition.getX()][selectedPosition.getY()].setPiece(board[lastPosition.getX()][lastPosition.getY()].getPiece());
                    board[lastPosition.getX()][lastPosition.getY()].setPiece(null);

                    isKingInDanger();
                    resetColorAtAllowedPosition(listOfCoordinates);
                    boardDisplay[lastPosition.getX()][lastPosition.getY()].setBackgroundResource(0);
                    resetColorAtLastPosition(lastPosition);
                    isFigureSelected = false;
                    firstPlayerTurn = !firstPlayerTurn;
                    checkForPawn();

                }else{
                    resetColorAtLastPosition(lastPosition);
                    resetColorAtAllowedPosition(listOfCoordinates);
                    isFigureSelected = false;
                }

            }else{
                if(board[selectedPosition.getX()][selectedPosition.getY()].getPiece() == null) {
                    isKingInDanger();
                    return;

                }else{
                    if(board[selectedPosition.getX()][selectedPosition.getY()].getPiece() !=null){
                        if(board[selectedPosition.getX()][selectedPosition.getY()].getPiece().isWhite() != firstPlayerTurn){
                            if(moveIsAllowed(listOfCoordinates , selectedPosition)){

                                saveBoard();
                                if(board[selectedPosition.getX()][selectedPosition.getY()].getPiece() instanceof King){
                                    if(board[selectedPosition.getX()][selectedPosition.getY()].getPiece().isWhite() != firstPlayerTurn){
                                        Intent intent = new Intent(ChessBoard.this, ResultSplash.class);
                                        boolean isInserted = databaseHelper.insertData(whitePlayer + getString(R.string.versus) + blackPlayer, blackPlayer, Calendar.getInstance().getTime().toString());
                                        if(isInserted){
                                            Toast.makeText(ChessBoard.this, getString(R.string.toastDataSuccessful), Toast.LENGTH_SHORT).show();
                                        }
                                        else{
                                            Toast.makeText(ChessBoard.this, getString(R.string.toastDataUnsuccessful), Toast.LENGTH_SHORT).show();

                                        }
                                        intent.putExtra(getString(R.string.bundleKeyWinner), blackPlayer);
                                        startActivity(intent);
                                    }
                                }
                                board[selectedPosition.getX()][selectedPosition.getY()].setPiece(board[lastPosition.getX()][lastPosition.getY()].getPiece());
                                board[lastPosition.getX()][lastPosition.getY()].setPiece(null);

                                resetColorAtAllowedPosition(listOfCoordinates);
                                boardDisplay[lastPosition.getX()][lastPosition.getY()].setBackgroundResource(0);
                                resetColorAtLastPosition(lastPosition);

                                isFigureSelected = false;
                                firstPlayerTurn = !firstPlayerTurn;
                                checkForPawn();
                            }else{
                                resetColorAtLastPosition(lastPosition);
                                resetColorAtAllowedPosition(listOfCoordinates);
                                isFigureSelected = false;
                            }

                        }else{
                            if(board[selectedPosition.getX()][selectedPosition.getY()].getPiece().isWhite() != firstPlayerTurn){
                                isKingInDanger();
                                return;
                            }

                            resetColorAtLastPosition(lastPosition);
                            resetColorAtAllowedPosition(listOfCoordinates);

                            listOfCoordinates.clear();
                            listOfCoordinates = board[selectedPosition.getX()][selectedPosition.getY()].getPiece().AllowedMoves(selectedPosition, board);
                            BackgroundBoardDisplay[selectedPosition.getX()][selectedPosition.getY()].setBackgroundResource(R.color.colorSelected);
                            setColorAtAllowedPosition(listOfCoordinates);
                            isFigureSelected = true;
                        }
                    }
                }
            }
        }

        isKingInDanger();
        lastPosition = new Coordinates(selectedPosition.getX(), selectedPosition.getY());
        setBoard();
    }

    public void saveBoard(){
        numberOfMoves++;
        previousMoves.add(numberOfMoves-1 , board2);

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                previousMoves.get(numberOfMoves-1)[i][j] = new Position(null);
            }
        }

        for(int g=0;g<8;g++){
            for(int h=0;h<8;h++){
                if(board[g][h].getPiece()==null){
                    previousMoves.get(numberOfMoves-1)[g][h].setPiece(null);
                }else{
                    previousMoves.get(numberOfMoves-1)[g][h].setPiece(board[g][h].getPiece());
                }
            }
        }

    }

    public void undo(View v){
        if(numberOfMoves>0) {

            for(int g=0;g<8;g++){
                for(int h=0;h<8;h++){
                    if(previousMoves.get(numberOfMoves-1)[g][h].getPiece()==null){
                        board[g][h].setPiece(null);
                    }else{
                        board[g][h].setPiece(previousMoves.get(numberOfMoves-1)[g][h].getPiece());
                    }
                }
            }
            numberOfMoves--;

            setBoard();
            for(int i=0;i<8;i++){
                for(int j=0;j<8;j++){
                    if((i+j)%2==0){
                        BackgroundBoardDisplay[i][j].setBackgroundResource(R.color.colorBoardDark);
                    }else{
                        BackgroundBoardDisplay[i][j].setBackgroundResource(R.color.colorBoardLight);
                    }
                }
            }
            isKingInDanger();
            firstPlayerTurn = !firstPlayerTurn;
        }
    }

    public void pawnPromotion(View v){
        int x = v.getId();
        switch (x){
            case R.id.pawn_queen :
                if(selectedPosition.getY() == 0){
                    board[selectedPosition.getX()][selectedPosition.getY()].setPiece(new Queen(true));
                    boardDisplay[selectedPosition.getX()][selectedPosition.getY()].setBackgroundResource(R.drawable.wqueen);
                }else{
                    board[selectedPosition.getX()][selectedPosition.getY()].setPiece(new Queen(false));
                    boardDisplay[selectedPosition.getX()][selectedPosition.getY()].setBackgroundResource(R.drawable.bqueen);
                }
                break;
            case R.id.pawn_rook :
                if(selectedPosition.getY() == 0){
                    board[selectedPosition.getX()][selectedPosition.getY()].setPiece(new Rook(true));
                    boardDisplay[selectedPosition.getX()][selectedPosition.getY()].setBackgroundResource(R.drawable.wrook);
                }else{
                    board[selectedPosition.getX()][selectedPosition.getY()].setPiece(new Rook(false));
                    boardDisplay[selectedPosition.getX()][selectedPosition.getY()].setBackgroundResource(R.drawable.brook);
                }
                break;
            case R.id.pawn_bishop :
                if(selectedPosition.getY() == 0){
                    board[selectedPosition.getX()][selectedPosition.getY()].setPiece(new Bishop(true));
                    boardDisplay[selectedPosition.getX()][selectedPosition.getY()].setBackgroundResource(R.drawable.wbishop);
                }else{
                    board[selectedPosition.getX()][selectedPosition.getY()].setPiece(new Bishop(false));
                    boardDisplay[selectedPosition.getX()][selectedPosition.getY()].setBackgroundResource(R.drawable.bbishop);
                }
                break;
            case R.id.pawn_knight :
                if(selectedPosition.getY() == 0){
                    board[selectedPosition.getX()][selectedPosition.getY()].setPiece(new Knight(true));
                    boardDisplay[selectedPosition.getX()][selectedPosition.getY()].setBackgroundResource(R.drawable.wknight);
                }else{
                    board[selectedPosition.getX()][selectedPosition.getY()].setPiece(new Knight(false));
                    boardDisplay[selectedPosition.getX()][selectedPosition.getY()].setBackgroundResource(R.drawable.bknight);

                }
                break;
        }
        pawnPromotion.setVisibility(View.INVISIBLE);
    }

    private void resetColorAtAllowedPosition(ArrayList<Coordinates> listOfCoordinates) {
        for(int i=0; i<listOfCoordinates.size(); i++){
            if((listOfCoordinates.get(i).getX() + listOfCoordinates.get(i).getY())%2==0){
                BackgroundBoardDisplay[listOfCoordinates.get(i).getX()][listOfCoordinates.get(i).getY()].setBackgroundResource(R.color.colorBoardDark);
            }else {
                BackgroundBoardDisplay[listOfCoordinates.get(i).getX()][listOfCoordinates.get(i).getY()].setBackgroundResource(R.color.colorBoardLight);
            }
        }
    }

    void setColorAtAllowedPosition(ArrayList<Coordinates> list){

        for(int i=0; i<list.size(); i++){
            if(board[list.get(i).getX()][list.get(i).getY()].getPiece() == null){
                BackgroundBoardDisplay[list.get(i).getX()][list.get(i).getY()].setBackgroundResource(R.color.Lime);
            }else{
                BackgroundBoardDisplay[list.get(i).getX()][list.get(i).getY()].setBackgroundResource(R.color.colorDanger);
            }
        }
    }

    private boolean moveIsAllowed(ArrayList<Coordinates> piece, Coordinates coordinate) {
        boolean Allowed = false;
        for(int i =0;i<piece.size();i++){
            if(piece.get(i).getX() == coordinate.getX()  &&  piece.get(i).getY() == coordinate.getY()){
                Allowed = true;
                break;
            }
        }
        return Allowed;
    }

    private void resetColorAtLastPosition(Coordinates lastPos){
        if((lastPos.getX() + lastPos.getY())%2==0){
            BackgroundBoardDisplay[lastPos.getX()][lastPos.getY()].setBackgroundResource(R.color.colorBoardDark);
        }else {
            BackgroundBoardDisplay[lastPos.getX()][lastPos.getY()].setBackgroundResource(R.color.colorBoardLight);
        }
    }

    private void isKingInDanger(){
        ArrayList<Coordinates> List = new ArrayList<>();

        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                if(board[i][j].getPiece() != null){
                    List.clear();
                    Coordinates c = new Coordinates(i,j);
                    List = board[i][j].getPiece().AllowedMoves(c, board);

                    for (int x=0;x<List.size();x++){
                        if(board[List.get(x).getX()][List.get(x).getY()].getPiece() instanceof King){

                            if((List.get(x).getX()+List.get(x).getY())%2==0){
                                BackgroundBoardDisplay[List.get(x).getX()][List.get(x).getY()].setBackgroundResource(R.color.colorBoardDark);
                            }else{
                                BackgroundBoardDisplay[List.get(x).getX()][List.get(x).getY()].setBackgroundResource(R.color.colorBoardLight);
                            }

                            if(board[i][j].getPiece().isWhite() != board[List.get(x).getX()][List.get(x).getY()].getPiece().isWhite()){
                                BackgroundBoardDisplay[List.get(x).getX()][List.get(x).getY()].setBackgroundResource(R.color.colorKingInDanger);
                            }

                        }
                    }
                }
            }
        }
    }

    private void checkForPawn(){
        if(board[selectedPosition.getX()][selectedPosition.getY()].getPiece() instanceof Pawn){
            if(board[selectedPosition.getX()][selectedPosition.getY()].getPiece().isWhite()){
                if(selectedPosition.getY() == 0){
                    pawnPromotion.setVisibility(View.VISIBLE);
                }
            }else{
                if(selectedPosition.getY() == 7){
                    pawnPromotion.setVisibility(View.VISIBLE);
                    pawnPromotion.setRotation(180);
                }
            }
        }
        isKingInDanger();
    }



}