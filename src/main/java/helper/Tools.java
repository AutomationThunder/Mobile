package helper;

import java.text.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class Tools {

    public String REMOVE_MULTIPLE_SPACES_AND_NEW_LINES(String text){
        String i = text.replace("\n"," ").replaceAll("\\s{2,}", " ").replaceAll("\\u00A0","").trim();
        return i;
    }

    public String nbspRemove(String text)
    {
        return text.trim().replaceAll("\\u00A0","");
    }

    public String convertToLetterCase(String text) {
        String firstLetter = text.substring(0,1);
        String exceptFirstLetter = text.substring(1,text.length());
        return firstLetter.toUpperCase() + exceptFirstLetter.toLowerCase();
    }

    public ArrayList<String> getDate(String date)
    {
        ArrayList<String> i = new ArrayList<>(3);
        LocalDateTime now = LocalDateTime.now();
        String day = date.trim().replace("today","");
        if(day.equalsIgnoreCase(""))
        {
            i.add(Integer.toString(now.getDayOfMonth()));
            i.add(Integer.toString(now.getMonthValue()));
            i.add(Integer.toString(now.getYear()));
        }else{
            long d = Integer.parseInt(day.replace("+",""));
            i.add(Integer.toString(now.plusDays(d).getDayOfMonth()));
            i.add(Integer.toString(now.plusDays(d).getMonthValue()));
            i.add(Integer.toString(now.plusDays(d).getYear()));
        }
        return i;
    }

    public String fixAmountIssue(String amount){
        String[] amtSplit=amount.split(" ");
        String amt=amtSplit[0]+" "+amtSplit[amtSplit.length-1];
        return amt;
    }

    public String pesoAmount(double amount)
    {
        NumberFormat n = NumberFormat.getCurrencyInstance(Locale.CHINA);
        String s = n.format(amount / 1);
        return s.replaceAll("￥","PHP ");
    }

    public String getDateInFormat(String day,String month,String year,String format) throws ParseException {
        String inputPattern="yyyy-M-dd";
        if(month.trim().length()>1){
            inputPattern="yyyy-MM-dd";
        }
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern); // or "YYYY-MM-DDThh:mm:ss±0000"
        String dateInString = year+"-"+month+"-"+day;
        Date date = inputFormat.parse(dateInString);
        SimpleDateFormat outputFormat = new SimpleDateFormat(format);
        return outputFormat.format(date);
    }

    public String currencyFormatter(double amount){
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        DecimalFormatSymbols decimalFormatSymbols = ((DecimalFormat) nf).getDecimalFormatSymbols();
        decimalFormatSymbols.setCurrencySymbol("");
        ((DecimalFormat) nf).setDecimalFormatSymbols(decimalFormatSymbols);
        return nf.format(amount).trim();
    }

    public String upto2Decimals(String amount){
        return new DecimalFormat("#0.00").format(amount);
    }
}