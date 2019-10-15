import React from 'react';
import MenuItem from 'app/shared/layout/menus/menu-item';
import { DropdownItem } from 'reactstrap';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { NavLink as Link } from 'react-router-dom';
import { NavDropdown } from './menu-components';

export const EntitiesMenu = props => (
  <NavDropdown icon="th-list" name="Entities" id="entity-menu">
    <MenuItem icon="asterisk" to="/entity/member-account-my-suffix">
      Member Account My Suffix
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/otp-history-my-suffix">
      Otp History My Suffix
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/category-my-suffix">
      Category My Suffix
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/campaign-category-my-suffix">
      Campaign Category My Suffix
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/fund-raiser-my-suffix">
      Fund Raiser My Suffix
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/currency-my-suffix">
      Currency My Suffix
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/campaign-my-suffix">
      Campaign My Suffix
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/campaign-payment-my-suffix">
      Campaign Payment My Suffix
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/campaign-transaction-my-suffix">
      Campaign Transaction My Suffix
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/investor-my-suffix">
      Investor My Suffix
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/investor-bank-my-suffix">
      Investor Bank My Suffix
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/company-bank-my-suffix">
      Company Bank My Suffix
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/fund-raiser-bank-my-suffix">
      Fund Raiser Bank My Suffix
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/bank-my-suffix">
      Bank My Suffix
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/investor-authorize-person-my-suffix">
      Investor Authorize Person My Suffix
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/campaign-investor-my-suffix">
      Campaign Investor My Suffix
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/investor-institution-my-suffix">
      Investor Institution My Suffix
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/investor-individu-my-suffix">
      Investor Individu My Suffix
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/tax-my-suffix">
      Tax My Suffix
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/investor-address-my-suffix">
      Investor Address My Suffix
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/country-my-suffix">
      Country My Suffix
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/province-my-suffix">
      Province My Suffix
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/city-my-suffix">
      City My Suffix
    </MenuItem>
    {/* jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here */}
  </NavDropdown>
);
