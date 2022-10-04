package oit.is.inudaisuki.springboot_samples.model;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ChamberMapper {

  @Select("SELECT id,userName,number from chamber where id = #{id}")
  Chamber selectById(int id);

  @Select("SELECT * from chamber where number = #{number}")
  ArrayList<Chamber> selectAllByNumber(int number);

  /**
   * DBのカラム名とjavaクラスのフィールド名が同じ場合はそのまま代入してくれる（大文字小文字の違いは無視される）
   * カラム名とフィールド名が異なる場合の対応も可能だが，いきなり複雑になるので，selectで指定するテーブル中のカラム名とクラスのフィールド名は同一になるよう設計することが望ましい
   *
   * @return
   */
  @Select("select CHAMBER.USERNAME,CHAMBER.NUMBER,USERINFO.HEIGHT from CHAMBER JOIN USERINFO ON CHAMBER.USERNAME=USERINFO.USERNAME;")
  ArrayList<ChamberUser> selectAllChamberUser();

  /**
   * #{userName}などはinsertの引数にあるChamberクラスのフィールドを表しています 引数に直接String
   * userNameなどと書いてもいけるはず
   * 下記のOptionsを指定すると，insert実行時にAuto incrementされたIDの情報を取得できるようになる useGeneratedKeys
   * = true -> Keyは自動生成されることを表す keyColumn : keyになるテーブルのカラム名 keyProperty :
   * keyになるJavaクラスのフィールド名
   *
   * @param chamber
   */
  @Insert("INSERT INTO chamber (userName,number) VALUES (#{userName},#{number});")
  @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
  void insertChamber(Chamber chamber);

  @Insert("INSERT INTO userinfo (userName,height) VALUES (#{userName},#{height});")
  void insertUserInfo(UserInfo userinfo);

}
