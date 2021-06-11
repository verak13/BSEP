import * as React from 'react';
import Paper from '@material-ui/core/Paper';
import {
  Chart,
  ArgumentAxis,
  ValueAxis,
  BarSeries,
  Title,
  Legend,
} from '@devexpress/dx-react-chart-material-ui';
import { withStyles } from '@material-ui/core/styles';
import { Stack, Animation } from '@devexpress/dx-react-chart';
import { months } from '../../utils/index';

const legendStyles = () => ({
    root: {
      display: 'flex',
      margin: 'auto',
      flexDirection: 'row',
    },
  });
  const legendRootBase = ({ classes, ...restProps }) => (
    <Legend.Root {...restProps} className={classes.root} />
  );
  const Root = withStyles(legendStyles, { name: 'LegendRoot' })(legendRootBase);
  const legendLabelStyles = () => ({
    label: {
      whiteSpace: 'nowrap',
    },
  });
  const legendLabelBase = ({ classes, ...restProps }) => (
    <Legend.Label className={classes.label} {...restProps} />
  );
  const Label = withStyles(legendLabelStyles, { name: 'LegendLabel' })(legendLabelBase);
  

export default function LogChart(props) {
    console.log(props.data)
    const createChartData = ({ data, start, end, year }) => {
      const obj = {
        errorLog : 0,
        loginErrorLog: 0,
        loginLog: 0,
        applicationLog: 0
      }
      let result = {}
      for(let i = start; i <= end; i++) {
        result[i] = {...obj, month: months[i] }
      }
      data.forEach(report => {
        const month = parseInt(report.date.split("-")[1]);
        const yearR = parseInt(report.date.split("-")[0]);
        if (month in result  && year == yearR) {
          result[month].errorLog += report.errorLogCount;
          result[month].loginErrorLog += report.loginErrorLogCount;
          result[month].loginLog += report.loginLogCount;
          result[month].applicationLog += report.applicationLogCount;
        }
      })
      return Object.values(result);
    }
    const chartData = createChartData(props);

    return (
    <Paper>
        <Chart
        data={chartData}
        >
        <ArgumentAxis />
        <ValueAxis />

        <BarSeries
            name="ERROR logs"
            valueField="errorLog"
            argumentField="month"
            color="#003f5c"
        />
        <BarSeries
            name="LOGIN logs"
            valueField="loginLog"
            argumentField="month"
            color="#58508d"
        />
        <BarSeries
            name="LOGIN ERROR logs"
            valueField="loginErrorLog"
            argumentField="month"
            color="#bc5090"
        />
        <BarSeries
            name="APPLICATION logs"
            valueField="applicationLog"
            argumentField="month"
            color="#ff6361"
        />
        <Animation />
        <Legend position="bottom" rootComponent={Root} labelComponent={Label} />
        <Title text={`Logs from ${months[props.start]} to ${months[props.end]} in ${props.year}`} />
        <Stack />
        </Chart>
    </Paper>
    );
}