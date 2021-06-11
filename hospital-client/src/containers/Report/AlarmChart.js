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
  


export default function AlarmChart(props) {

    const createChartData = ({ data, start, end, year }) => {
      const obj = {
        bruteForceAlarms : 0,
        errorLogAlarms: 0,
        inactiveUserAlarms: 0,
        ipAlarms: 0
      }
      let result = {}
      for(let i = start; i <= end; i++) {
        result[i] = {...obj, month: months[i] }
      }
      data.forEach(report => {
        const month = parseInt(report.date.split("-")[1]);
        const yearR = parseInt(report.date.split("-")[0]);
        if (month in result && year == yearR) {
          result[month].bruteForceAlarms += report.bruteForceAlarmsTriggered;
          result[month].errorLogAlarms += report.errorLogAlarmsTriggered;
          result[month].inactiveUserAlarms += report.inactiveUserAlarmsTriggered;
          result[month].ipAlarms += report.ipAlarmsTriggered;
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
            name="Brute force alarms"
            valueField="bruteForceAlarms"
            argumentField="month"
            color="#003f5c"
        />
        <BarSeries
            name="Error log alarms"
            valueField="errorLogAlarms"
            argumentField="month"
            color="#58508d"
        />
        <BarSeries
            name="Inactive user alarms"
            valueField="inactiveUserAlarms"
            argumentField="month"
            color="#bc5090"
        />
        <BarSeries
            name="Blacklisted ip alarms"
            valueField="ipAlarms"
            argumentField="month"
            color="#ff6361"
        />
        <Animation />
        <Legend position="bottom" rootComponent={Root} labelComponent={Label} />
        <Title text={`Alarms triggered from ${months[props.start]} to ${months[props.end]} in ${props.year}`} />
        <Stack />
        </Chart>
    </Paper>
    );
}