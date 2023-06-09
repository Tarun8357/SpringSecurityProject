package CalculationHandler;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/calculator")
public class CalculatorServlet extends HttpServlet {
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    resp.setHeader("Access-Control-Allow-Origin",  "*");
    resp.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
    PrintWriter out = resp.getWriter();
    resp.setContentType("application/json");
    Gson gson = new Gson();
    StringBuilder stringBuilder = new StringBuilder();
    BufferedReader bufferedReader = req.getReader();
    String line;
    while((line= bufferedReader.readLine())!=null){
      stringBuilder.append(line);
    }
    String payload = stringBuilder.toString();
    ArrayList<CalculatorDTO> paramList = gson.fromJson(payload, new TypeToken<ArrayList<CalculatorDTO>>(){}.getType());
    out.print(CalculatorModel.calculation(paramList));
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    super.doGet(req, resp);
  }
}
