package it.uniba.query;


/**
 * The builder interface.
 *
 * <i>&#60;Entity&#62;</i>
 */
interface IQueryBuilder {

	IQueryBuilder buildSelect();

	IQueryBuilder buildTable();

	IQueryBuilder buildWhere();

	IQueryBuilder buildGroupBy();

	IQueryBuilder buildOrderBy();

	IQueryBuilder buildLimit();

	it.uniba.query.QueryTable getResult();
}
