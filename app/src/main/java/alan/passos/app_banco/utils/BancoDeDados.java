package alan.passos.app_banco.utils;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import alan.passos.app_banco.DAO.ProdutoDAO;
import alan.passos.app_banco.DAO.UserDAO;
import alan.passos.app_banco.entidades.ProdutoEntity;
import alan.passos.app_banco.entidades.UserEntity;


@Database(entities = {UserEntity.class, ProdutoEntity.class}, version = 1)

public abstract class BancoDeDados extends RoomDatabase {

    public abstract UserDAO getUserDao();

    public abstract ProdutoDAO getProdutoDao();

}
