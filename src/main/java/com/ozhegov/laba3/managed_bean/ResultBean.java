package com.ozhegov.laba3.managed_bean;

import com.ozhegov.laba3.dao.PointDAO;
import com.ozhegov.laba3.model.Point;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.ValidatorException;
import jakarta.inject.Named;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Data
@NoArgsConstructor
@Named("resultBean")
@ApplicationScoped
public class ResultBean {
    private PointDAO pointDAO = new PointDAO();
    private Point point = new Point();

    public String savePoint(){
        long timeStart = System.nanoTime();

        calculateResult();
        point.setExecutionTime(System.nanoTime() - timeStart);
        pointDAO.save(point);

        point = new Point();
        return null;
    }
    public void savePointFromJson(String x, String y, String r){
        final Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        System.out.println(params);
        DecimalFormat decimalFormat = (DecimalFormat) DecimalFormat.getInstance(Locale.US);
        try{
            double xVal = decimalFormat.parse(params.get("x")).doubleValue();
            double yVal = decimalFormat.parse(params.get("y")).doubleValue();
            double rVal = decimalFormat.parse(params.get("r")).doubleValue();

            point.setX(String.valueOf(xVal));
            point.setY(yVal);
            point.setR(rVal);
            savePoint();
        }catch (IllegalArgumentException e){
            e.printStackTrace();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Point> getPoints(){
        return pointDAO.getAll();
    }
    private void calculateResult(){
        double x = point.getX();
        double y = point.getY();
        double r = point.getR();
        if(r < 2 || r > 5 || y < -3 || y > 3)
            throw new ValidatorException(new FacesMessage("Координата y должна быть в диапазоне [-3,3]!"));

        double line = y + x -r;
        double circle = Math.pow(x, 2) + Math.pow(y, 2) - Math.pow(r, 2);

        if((x<=0 && y>= 0 && circle<=0) || (x>=0 && y>=0 && line<=0) || (x>=0 && y<=0 && x<=r && y>=(-r/2)))
            point.setResult("попал");
        else
            point.setResult("не попал");
    }
}
