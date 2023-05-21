package alan.passos.app_banco.DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import alan.passos.app_banco.entidades.ProdutoEntity;

@Dao
public interface ProdutoDAO {

    @Insert
    void insert(ProdutoEntity produto);

    @Query("SELECT * FROM produto ")
    List<ProdutoEntity> getProdutoAll();

    @Query("SELECT * FROM produto where id_Produto =:id")
    ProdutoEntity getProdutoId(int id);

    @Update()
    void updateProduto(ProdutoEntity produto);

}
