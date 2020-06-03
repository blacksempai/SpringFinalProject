package com.javacourse.dao.implementation;

import com.javacourse.dao.InspectorDAO;
import com.javacourse.dao.ReportDAO;
import com.javacourse.dao.UserDAO;
import com.javacourse.dao.factory.MySqlDBConnection;
import com.javacourse.model.entities.Inspector;
import com.javacourse.model.entities.User;
import com.javacourse.model.entities.report.*;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class ReportDAOMySQL implements ReportDAO {
    private static Logger logger = Logger.getLogger(ReportDAOMySQL.class);

    @Override
    public void create(Report report) {
        String sql = "INSERT INTO report(user_id,inspector_id,date,status,review) VALUES(?,?,?,?,?);";
        try (Connection connection = MySqlDBConnection.getConnection()) {
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, report.getUser().getId());
            statement.setInt(2, report.getInspector().getId());
            statement.setTimestamp(3, report.getDeclarationSubmissionDate());
            statement.setString(4, report.getStatus().toString());
            statement.setString(5, report.getReview());
            statement.execute();
            ResultSet rs = statement.getGeneratedKeys();
            if(rs.next())
                report.setId(rs.getInt(1));
            else
                throw new SQLException("Error getting generated key");
            addReportBody(connection, report);
            connection.commit();
        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
    }

    private void addReportBody(Connection connection, Report report) throws SQLException {
        ReportBody body = report.getReportBody();
        String sql = "INSERT INTO report_body" +
                "(report_id) VALUES(?);";
        PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        statement.setInt(1,report.getId());
        statement.execute();
        ResultSet rs = statement.getGeneratedKeys();
        if(rs.next())
            body.setId(rs.getInt(1));
        else
            throw new SQLException("Error getting generated key");
        addInfo(connection, body);
        addGeneral(connection, body);
        addFirstGroup(connection, body);
        addSecondGroup(connection, body);
        addThirdGroup(connection, body);
        addReportError(connection, body);
    }

    private void addInfo(Connection connection, ReportBody body) throws SQLException {
        Info info = body.getInfo();
        String sql = "INSERT INTO report_info(report_info_id,type,period,year,specified_period,specified_year,authority_name) VALUES(?,?,?,?,?,?,?);";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, body.getId());
        statement.setString(2, info.getType().toString());
        statement.setString(3, info.getPeriod().toString());
        statement.setInt(4, info.getYear());
        statement.setString(5, info.getSpecifiedPeriod().toString());
        statement.setInt(6, info.getSpecifiedYear());
        statement.setString(7, info.getAuthorityName());
        statement.execute();
    }

    private void addGeneral(Connection connection, ReportBody body) throws SQLException {
        General general = body.getGeneral();
        String sql = "INSERT INTO report_general(report_general_id,employees_number) VALUES(?,?);";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, body.getId());
        statement.setInt(2, general.getEmployeesNumber());
        statement.execute();
        addActivities(body.getId(), general.getActivities(), connection);
    }

    private void addActivities(int id, List<BusinessActivity> activities, Connection connection) throws SQLException {
        String sql = "INSERT INTO business_activity(report_general_id, code, name) VALUES(?,?,?);";

        int count = 0;
        for (BusinessActivity a : activities) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.setString(2, a.getCode());
            statement.setString(3, a.getName());
            statement.execute();
            /*
            statement.addBatch();
            count++;
            // execute every 100 rows or less
            if (count % 100 == 0 || count == activities.size()) {
                statement.executeBatch();
            }*/
        }
    }

    private void addFirstGroup(Connection connection, ReportBody body) throws SQLException {
        FirstGroup firstGroup = body.getFirstGroup();
        String sql = "INSERT INTO first_group" +
                "(first_group_id,first_quarter,second_quarter,third_quarter,fourth_quarter,income,taxed) VALUES(?,?,?,?,?,?,?);";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, body.getId());
        statement.setDouble(2, firstGroup.getFirstQuarter());
        statement.setDouble(3, firstGroup.getSecondQuarter());
        statement.setDouble(4, firstGroup.getThirdQuarter());
        statement.setDouble(5, firstGroup.getForthQuarter());
        statement.setDouble(6, firstGroup.getIncomeVolume01());
        statement.setDouble(7, firstGroup.getTaxedIncomeVolume02());
        statement.execute();
    }

    private void addSecondGroup(Connection connection, ReportBody body) throws SQLException {
        SecondGroup secondGroup = body.getSecondGroup();
        String sql = "INSERT INTO second_group" +
                "(second_group_id,first_quarter,second_quarter,third_quarter,fourth_quarter,income,taxed) VALUES(?,?,?,?,?,?,?);";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, body.getId());
        statement.setDouble(2, secondGroup.getFirstQuarter());
        statement.setDouble(3, secondGroup.getSecondQuarter());
        statement.setDouble(4, secondGroup.getThirdQuarter());
        statement.setDouble(5, secondGroup.getForthQuarter());
        statement.setDouble(6, secondGroup.getIncomeVolume03());
        statement.setDouble(7, secondGroup.getTaxedIncomeVolume04());
        statement.execute();
    }

    private void addThirdGroup(Connection connection, ReportBody body) throws SQLException {
        ThirdGroup thirdGroup = body.getThirdGroup();
        String sql = "INSERT INTO third_group(third_group_id,3_percent,5_percent,15_percent) VALUES(?,?,?,?);";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, body.getId());
        statement.setDouble(2, thirdGroup.getThreePercentTaxed05());
        statement.setDouble(3, thirdGroup.getFivePercentTaxed06());
        statement.setDouble(4, thirdGroup.getFifteenPercentTaxed07());
        statement.execute();
    }

    private void addReportError(Connection connection, ReportBody body) throws SQLException {
        ErrorReport errorReport = body.getErrorReport();
        String sql = "INSERT INTO report_errors(report_errors_id,single_tax,spec_tax,fine_percent,penny_sum) VALUES(?,?,?,?,?);";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, body.getId());
        statement.setDouble(2, errorReport.getSingleTax15());
        statement.setDouble(3, errorReport.getSpecifiedTax16());
        statement.setDouble(4, errorReport.getFinePercent());
        statement.setDouble(5, errorReport.getPennySum20());
        statement.execute();
    }

    @Override
    public void update(Report report) {
        String sql = "UPDATE report SET user_id=?,inspector_id=?,date=?,status=?,review=? WHERE report_id=?;";
        try (Connection connection = MySqlDBConnection.getConnection()) {
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, report.getUser().getId());
            statement.setInt(2, report.getInspector().getId());
            statement.setTimestamp(3, report.getDeclarationSubmissionDate());
            statement.setString(4, report.getStatus().toString());
            statement.setString(5, report.getReview());
            statement.setInt(6, report.getId());
            updateReportBody(connection, report.getReportBody());
            statement.execute();
            connection.commit();
        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
    }

    private void updateReportBody(Connection connection, ReportBody body) throws SQLException {
        updateInfo(connection, body.getInfo());
        updateGeneral(connection, body.getGeneral());
        updateFirstGroup(connection, body.getFirstGroup());
        updateSecondGroup(connection, body.getSecondGroup());
        updateThirdGroup(connection, body.getThirdGroup());
        updateReportError(connection, body.getErrorReport());
    }

    private void updateInfo(Connection connection, Info info) throws SQLException {
        String sql = "UPDATE report_info SET " +
                "type=?,period=?,year=?,specified_period=?,specified_year=?,authority_name=? WHERE report_info_id=?;";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, info.getType().toString());
        statement.setString(2, info.getPeriod().toString());
        statement.setInt(3, info.getYear());
        statement.setString(4, info.getSpecifiedPeriod().toString());
        statement.setInt(5, info.getSpecifiedYear());
        statement.setString(6, info.getAuthorityName());
        statement.setInt(7, info.getId());
        statement.execute();
    }

    private void updateGeneral(Connection connection, General general) throws SQLException {
        String sql = "UPDATE report_general SET employees_number=? WHERE report_general_id=?;";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, general.getEmployeesNumber());
        statement.setInt(2, general.getId());
        statement.execute();
        updateActivities(general.getId(), general.getActivities(), connection);
    }

    private void updateActivities(int id, List<BusinessActivity> activities, Connection connection) throws SQLException {
        String sql = "UPDATE business_activity SET report_general_id=?, code=?, name=? WHERE business_activity_id=?;";
        PreparedStatement statement = connection.prepareStatement(sql);
        int count = 0;
        for (BusinessActivity a : activities) {
            statement.setInt(1, id);
            statement.setString(2, a.getCode());
            statement.setString(3, a.getName());
            statement.setInt(4, a.getId());
            statement.addBatch();
            count++;
            // execute every 100 rows or less
            if (count % 100 == 0 || count == activities.size()) {
                statement.executeBatch();
            }
        }
    }

    private void updateFirstGroup(Connection connection, FirstGroup firstGroup) throws SQLException {
        String sql = "UPDATE first_group SET " +
                "first_quarter=?, second_quarter=?, third_quarter=?, fourth_quarter=?, income=?, taxed=?" +
                " WHERE first_group_id=?;";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setDouble(1, firstGroup.getFirstQuarter());
        statement.setDouble(2, firstGroup.getSecondQuarter());
        statement.setDouble(3, firstGroup.getThirdQuarter());
        statement.setDouble(4, firstGroup.getForthQuarter());
        statement.setDouble(5, firstGroup.getIncomeVolume01());
        statement.setDouble(6, firstGroup.getTaxedIncomeVolume02());
        statement.setInt(7,firstGroup.getId());
        statement.execute();
    }

    private void updateSecondGroup(Connection connection, SecondGroup secondGroup) throws SQLException {
        String sql = "UPDATE second_group SET " +
                "first_quarter=?, second_quarter=?, third_quarter=?, fourth_quarter=?, income=?, taxed=?" +
                " WHERE second_group_id=?;";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setDouble(1, secondGroup.getFirstQuarter());
        statement.setDouble(2, secondGroup.getSecondQuarter());
        statement.setDouble(3, secondGroup.getThirdQuarter());
        statement.setDouble(4, secondGroup.getForthQuarter());
        statement.setDouble(5, secondGroup.getIncomeVolume03());
        statement.setDouble(6, secondGroup.getTaxedIncomeVolume04());
        statement.setInt(7, secondGroup.getId());
        statement.execute();
    }

    private void updateThirdGroup(Connection connection, ThirdGroup thirdGroup) throws SQLException {
        String sql = "UPDATE third_group SET 3_percent=?, 5_percent=?, 15_percent=? WHERE third_group_id=?;";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setDouble(1, thirdGroup.getThreePercentTaxed05());
        statement.setDouble(2, thirdGroup.getFivePercentTaxed06());
        statement.setDouble(3, thirdGroup.getFifteenPercentTaxed07());
        statement.setInt(4, thirdGroup.getId());
        statement.execute();
    }

    private void updateReportError(Connection connection, ErrorReport errorReport) throws SQLException {
        String sql = "UPDATE report_errors SET single_tax=?, spec_tax=?, fine_percent=?, penny_sum=?" +
                " WHERE report_errors_id=?;";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setDouble(1, errorReport.getSingleTax15());
        statement.setDouble(2, errorReport.getSpecifiedTax16());
        statement.setDouble(3, errorReport.getFinePercent());
        statement.setDouble(4, errorReport.getPennySum20());
        statement.setInt(5, errorReport.getId());
        statement.execute();
    }

    @Override
    public void delete(Report report) {
        String sql = "DELETE FROM report WHERE report_id=?;";//CASCADE
        try (Connection connection = MySqlDBConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, report.getId());
            statement.execute();
        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public List<Report> getReportsByUser(User user) {
        String sql = "SELECT * FROM report R JOIN report_body B ON (R.report_id = B.report_body_id) WHERE user_id=?;";
        List<Report> reports = new ArrayList<>();
        try (Connection connection = MySqlDBConnection.getConnection()) {
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, user.getId());
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Report report = new Report();
                report.setId(rs.getInt("report_id"));
                report.setUser(user);
                report.setInspector(getInspector(rs.getInt("inspector_id")));
                report.setDeclarationSubmissionDate(rs.getTimestamp("date"));
                report.setStatus(Report.Status.valueOf(rs.getString("status")));
                report.setReview(rs.getString("review"));
                report.setReportBody(getReportBody(connection, rs.getInt("report_body_id")));
                reports.add(report);
            }
            connection.commit();
        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return reports;
    }

    @Override
    public List<Report> getReportsByInspector(Inspector inspector) {
        String sql = "SELECT * FROM report R JOIN report_body B ON (R.report_id = B.report_body_id) WHERE inspector_id=?;";
        List<Report> reports = new ArrayList<>();
        try (Connection connection = MySqlDBConnection.getConnection()) {
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, inspector.getId());
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Report report = new Report();
                report.setId(rs.getInt("report_id"));
                report.setUser(getUser(rs.getInt("user_id")));
                report.setInspector(inspector);
                report.setDeclarationSubmissionDate(rs.getTimestamp("date"));
                report.setStatus(Report.Status.valueOf(rs.getString("status")));
                report.setReview(rs.getString("review"));
                report.setReportBody(getReportBody(connection, rs.getInt("report_body_id")));
                reports.add(report);
            }
            connection.commit();
        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return reports;
    }

    private ReportBody getReportBody(Connection connection, int id) throws SQLException {
        String sql = "SELECT * FROM report_body R \n" +
                "JOIN report_info Ri ON (R.report_body_id = Ri.report_info_id)\n" +
                "JOIN report_general Rg ON (R.report_body_id = Rg.report_general_id)\n" +
                "JOIN third_group R3 ON (R.report_body_id = R3.third_group_id)\n" +
                "JOIN report_errors Re ON (R.report_body_id = Re.report_errors_id)\n" +
                "WHERE report_body_id=?;";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet rs = statement.executeQuery();
        if (rs.next()) {
            ReportBody reportBody = new ReportBody();
            Info info = new Info();
            General general = new General();
            FirstGroup firstGroup;
            SecondGroup secondGroup;
            ThirdGroup thirdGroup = new ThirdGroup();
            ErrorReport errorReport = new ErrorReport();
            info.setId(rs.getInt("report_info_id"));
            info.setType(Info.Type.valueOf(rs.getString("type")));
            info.setPeriod(Info.Period.valueOf(rs.getString("period")));
            info.setYear((short) rs.getInt("year"));
            info.setSpecifiedPeriod(Info.Period.valueOf(rs.getString("specified_period")));
            info.setSpecifiedYear((short) rs.getInt("specified_year"));
            info.setAuthorityName(rs.getString("authority_name"));
            general.setId(rs.getInt("report_general_id"));
            general.setEmployeesNumber(rs.getInt("employees_number"));
            general.setActivities(getActivities(connection, general.getId()));
            firstGroup = getFirstGroup(connection, rs.getInt("report_body_id"));
            secondGroup = getSecondGroup(connection, rs.getInt("report_body_id"));
            thirdGroup.setId(rs.getInt("third_group_id"));
            thirdGroup.setThreePercentTaxed05(rs.getDouble("3_percent"));
            thirdGroup.setFivePercentTaxed06(rs.getDouble("5_percent"));
            thirdGroup.setFifteenPercentTaxed07(rs.getDouble("15_percent"));
            errorReport.setId(rs.getInt("report_errors_id"));
            errorReport.setSingleTax15(rs.getDouble("single_tax"));
            errorReport.setSpecifiedTax16(rs.getDouble("spec_tax"));
            errorReport.setFinePercent((short) rs.getInt("fine_percent"));
            errorReport.setPennySum20(rs.getDouble("penny_sum"));
            reportBody.setInfo(info);
            reportBody.setGeneral(general);
            reportBody.setFirstGroup(firstGroup);
            reportBody.setSecondGroup(secondGroup);
            reportBody.setThirdGroup(thirdGroup);
            reportBody.setErrorReport(errorReport);
            return reportBody;
        }
        else
            throw new SQLException("No Report was found on those id");
    }

    private List<BusinessActivity> getActivities(Connection connection, int id) throws SQLException {
        List<BusinessActivity> activities = new ArrayList<>();
        String sql = "SELECT * FROM business_activity WHERE report_general_id=?;";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet rs = statement.executeQuery();
        while (rs.next()){
            BusinessActivity a = new BusinessActivity();
            a.setId(rs.getInt("business_activity_id"));
            a.setName(rs.getString("name"));
            a.setCode(rs.getString("code"));
            activities.add(a);
        }
        return activities;
    }

    private FirstGroup getFirstGroup(Connection connection, int id) throws SQLException {
        String sql = "SELECT * FROM first_group WHERE first_group_id=?;";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet rs = statement.executeQuery();
        if (rs.next()) {
            FirstGroup firstGroup = new FirstGroup();
            firstGroup.setId(id);
            firstGroup.setFirstQuarter(rs.getDouble("first_quarter"));
            firstGroup.setSecondQuarter(rs.getDouble("second_quarter"));
            firstGroup.setThirdQuarter(rs.getDouble("third_quarter"));
            firstGroup.setForthQuarter(rs.getDouble("fourth_quarter"));
            firstGroup.setIncomeVolume01(rs.getDouble("income"));
            firstGroup.setTaxedIncomeVolume02(rs.getDouble("taxed"));
            return firstGroup;
        }
        else
            throw new SQLException();
    }

    private SecondGroup getSecondGroup(Connection connection, int id) throws SQLException {
        String sql = "SELECT * FROM second_group WHERE second_group_id=?;";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet rs = statement.executeQuery();
        if (rs.next()) {
            SecondGroup secondGroup = new SecondGroup();
            secondGroup.setId(id);
            secondGroup.setFirstQuarter(rs.getDouble("first_quarter"));
            secondGroup.setSecondQuarter(rs.getDouble("second_quarter"));
            secondGroup.setThirdQuarter(rs.getDouble("third_quarter"));
            secondGroup.setForthQuarter(rs.getDouble("fourth_quarter"));
            secondGroup.setIncomeVolume03(rs.getDouble("income"));
            secondGroup.setTaxedIncomeVolume04(rs.getDouble("taxed"));
            return secondGroup;
        }
        else
            throw new SQLException();
    }

    private User getUser(Integer id){
        UserDAO userDAO = new UserDAOMySQL();
        return userDAO.getById(id);
    }

    private Inspector getInspector(Integer id){
        InspectorDAO inspectorDAO = new InspectorDAOMySQL();
        return inspectorDAO.getById(id);
    }

}