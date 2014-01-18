package com.empty.apl.db.schema;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.empty.framework.db.schema.AbstractSchemaDefinition;

/**
 * AP側のテーブル構造と初期データを定義。
 * @author id:language_and_engineering
 *
 */
public class SchemaDefinition extends AbstractSchemaDefinition
{
    // NOTE:
    // ・各テーブルの主キーは「id」。
    // ・SQLiteのカラムで定義可能な型については，下記を参照
    //     http://www.sqlite.org/datatype3.html
    // ・アプリのインストール（初期化）アクティビティから呼び出される。

    @Override
    public void define_tables()
    {


    }


    @Override
    public void init_db_data(SQLiteDatabase db, Context context)
    {
    }

}
