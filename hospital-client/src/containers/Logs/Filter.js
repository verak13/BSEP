import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import FilterListIcon from '@material-ui/icons/FilterList';
import Input from '@material-ui/core/Input';
import InputAdornment from '@material-ui/core/InputAdornment';

import { getLogs } from '../../store/actions/logActions';


function Filter(props) {
  
	function filter(e) {
        if(e.keyCode == 13) {
            const val = e.target.value;
            props.search[props.type] = val;
            props.getLogs({ pageSize: props.pageSize, page: 0, search: props.search});
        }
	}

	return (
		<Input
          id="input-with-icon-adornment"
          startAdornment={
            <InputAdornment position="start">
              <FilterListIcon />
            </InputAdornment>
          }
          onKeyDown={e => filter(e)}
        />
	);
}


const mapStateToProps = state => ({
})

const mapDispatchToProps = {
    getLogs
}



export default connect(mapStateToProps, mapDispatchToProps)(Filter);