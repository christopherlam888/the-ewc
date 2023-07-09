import com.example.the_ewc.Term
import retrofit2.Call
import retrofit2.http.GET

interface API {
    @GET("glossary/all/")
    fun getData(): Call<ArrayList<Term>>
}
