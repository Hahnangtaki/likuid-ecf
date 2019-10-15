import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './currency-my-suffix.reducer';
import { ICurrencyMySuffix } from 'app/shared/model/currency-my-suffix.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ICurrencyMySuffixProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class CurrencyMySuffix extends React.Component<ICurrencyMySuffixProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { currencyList, match } = this.props;
    return (
      <div>
        <h2 id="currency-my-suffix-heading">
          Currencies
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Create a new Currency
          </Link>
        </h2>
        <div className="table-responsive">
          {currencyList && currencyList.length > 0 ? (
            <Table responsive aria-describedby="currency-my-suffix-heading">
              <thead>
                <tr>
                  <th>ID</th>
                  <th>Currency Code</th>
                  <th>Currency Name</th>
                  <th>Currency Symbol</th>
                  <th />
                </tr>
              </thead>
              <tbody>
                {currencyList.map((currency, i) => (
                  <tr key={`entity-${i}`}>
                    <td>
                      <Button tag={Link} to={`${match.url}/${currency.id}`} color="link" size="sm">
                        {currency.id}
                      </Button>
                    </td>
                    <td>{currency.currencyCode}</td>
                    <td>{currency.currencyName}</td>
                    <td>{currency.currencySymbol}</td>
                    <td className="text-right">
                      <div className="btn-group flex-btn-group-container">
                        <Button tag={Link} to={`${match.url}/${currency.id}`} color="info" size="sm">
                          <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${currency.id}/edit`} color="primary" size="sm">
                          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${currency.id}/delete`} color="danger" size="sm">
                          <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                        </Button>
                      </div>
                    </td>
                  </tr>
                ))}
              </tbody>
            </Table>
          ) : (
            <div className="alert alert-warning">No Currencies found</div>
          )}
        </div>
      </div>
    );
  }
}

const mapStateToProps = ({ currency }: IRootState) => ({
  currencyList: currency.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(CurrencyMySuffix);
