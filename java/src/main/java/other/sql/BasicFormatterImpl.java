package other.sql;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.StringTokenizer;

public class BasicFormatterImpl {
    public static void main(String[] args) {
        System.out.println(new BasicFormatterImpl()
                .format("create table ss_ls_hxsq\n" +
                        "as\n" +
                        "select  a.DYDM,\n" +
                        "       a.DYMC,\n" +
                        "       TTZQ,\n" +
                        "       a.YCYL,\n" +
                        "       a.YCYLMC,\n" +
                        "               SQCLDYCD_ZBXZ,\n" +
                        "               SQCLDYCD_PJJG,\n" +
                        "               SQCLDYCD_DZBDF,\n" +
                        "               SQCLDYCD_QZDF,\n" +
                        "               YLXS_ZBXZ,\n" +
                        "               YLXS_PJJG,\n" +
                        "               YLXS_DZBDF,\n" +
                        "               YLXS_QZDF,\n" +
                        "               \n" +
                        "               LJDJL_ZBXZ,\n" +
                        "               LJDJL_PJJG,\n" +
                        "               LJDJL_DZBDF,\n" +
                        "               LJDJL_QZDF, \n" +
                        "                \n" +
                        "               HSSSL_ZBXZ,\n" +
                        "               HSSSL_PJJG,\n" +
                        "               HSSSL_DZBDF,\n" +
                        "               HSSSL_QZDF,               \n" +
                        "               \n" +
                        "               CSL_ZBXZ,\n" +
                        "               CSL_PJJG,\n" +
                        "               CSL_DZBDF,\n" +
                        "               CSL_QZDF,\n" +
                        "\n" +
                        "               DYCZCB_ZBXZ,\n" +
                        "               DYCZCB_PJJG,\n" +
                        "               DYCZCB_DZBDF,\n" +
                        "               DYCZCB_QZDF,\n" +
                        "       ZDF,\n" +
                        "       rank,     \n" +
                        "       case\n" +
                        "             when lag(bfb) over(order by bfb) <= 20 or lag(bfb)\n" +
                        "              over(order by bfb) is null then\n" +
                        "              '好'\n" +
                        "             when lag(bfb) over(order by bfb) > 20 and lag(bfb)\n" +
                        "              over(order by bfb) <= 70 then\n" +
                        "              '中'\n" +
                        "             else\n" +
                        "              '差'\n" +
                        "           end zhpjjg,ljdcyl\n" +
                        "           \n" +
                        "from\n" +
                        "(\n" +
                        "select a.*,\n" +
                        "                   sum(dydzcl) over(order by rank) ljdcyl,\n" +
                        "                   round((sum(dydzcl) over(order by rank)) * 100 / 25553.21,\n" +
                        "                         2) bfb    \n" +
                        "from (\n" +
                        "SELECT a.DYDM,\n" +
                        "       a.DYMC,\n" +
                        "       TTZQ,\n" +
                        "       a.YCYL,\n" +
                        "       a.YCYLMC,\n" +
                        "               SQCLDYCD_ZBXZ,\n" +
                        "               SQCLDYCD_PJJG,\n" +
                        "               SQCLDYCD_DZBDF,\n" +
                        "               SQCLDYCD_QZDF,\n" +
                        "               YLXS_ZBXZ,\n" +
                        "               YLXS_PJJG,\n" +
                        "               YLXS_DZBDF,\n" +
                        "               YLXS_QZDF,\n" +
                        "               \n" +
                        "               LJDJL_ZBXZ,\n" +
                        "               LJDJL_PJJG,\n" +
                        "               LJDJL_DZBDF,\n" +
                        "               LJDJL_QZDF, \n" +
                        "                \n" +
                        "               HSSSL_ZBXZ,\n" +
                        "               HSSSL_PJJG,\n" +
                        "               HSSSL_DZBDF,\n" +
                        "               HSSSL_QZDF,               \n" +
                        "               \n" +
                        "               CSL_ZBXZ,\n" +
                        "               CSL_PJJG,\n" +
                        "               CSL_DZBDF,\n" +
                        "               CSL_QZDF,\n" +
                        "\n" +
                        "               DYCZCB_ZBXZ,\n" +
                        "               DYCZCB_PJJG,\n" +
                        "               DYCZCB_DZBDF,\n" +
                        "               DYCZCB_QZDF,\n" +
                        "       ZDF,\n" +
                        "       dense_rank() over(order by zdf desc) rank,\n" +
                        "      dydzcl\n" +
                        "  FROM (select DYDM,\n" +
                        "               DYMC,\n" +
                        "               TTZQ,\n" +
                        "               YCYL,\n" +
                        "               YCYLMC,\n" +
                        "               SQCLDYCD_ZBXZ,\n" +
                        "               SQCLDYCD_PJJG,\n" +
                        "               SQCLDYCD_DZBDF,\n" +
                        "               SQCLDYCD_QZDF,\n" +
                        "               YLXS_ZBXZ,\n" +
                        "               YLXS_PJJG,\n" +
                        "               YLXS_DZBDF,\n" +
                        "               YLXS_QZDF,\n" +
                        "               \n" +
                        "               LJDJL_ZBXZ,\n" +
                        "               LJDJL_PJJG,\n" +
                        "               LJDJL_DZBDF,\n" +
                        "               LJDJL_QZDF, \n" +
                        "                \n" +
                        "               HSSSL_ZBXZ,\n" +
                        "               HSSSL_PJJG,\n" +
                        "               HSSSL_DZBDF,\n" +
                        "               HSSSL_QZDF,               \n" +
                        "               \n" +
                        "               CSL_ZBXZ,\n" +
                        "               CSL_PJJG,\n" +
                        "               CSL_DZBDF,\n" +
                        "               CSL_QZDF,\n" +
                        "\n" +
                        "               DYCZCB_ZBXZ,\n" +
                        "               DYCZCB_PJJG,\n" +
                        "               DYCZCB_DZBDF,\n" +
                        "               DYCZCB_QZDF,\n" +
                        "               SQCLDYCD_QZDF + YLXS_QZDF +LJDJL_QZDF+HSSSL_QZDF+ CSL_QZDF + DYCZCB_QZDF ZDF\n" +
                        "          FROM (                                          \n" +
                        "          select *\n" +
                        "                  from (\n" +
                        "                  \n" +
                        "                  \n" +
                        "                  \n" +
                        "                  \n" +
                        "               select a.dydm,\n" +
                        "       a.dymc,\n" +
                        "       hs ttzq,\n" +
                        "       'hxsq' ycyl,\n" +
                        "       '后续水驱' ycylMC,\n" +
                        "       a.zbxdm,\n" +
                        "       a.zbxz,\n" +
                        "       pjjg,\n" +
                        "       f_pj_dzbdf_hxsq(hs, zbxdm, zbxz, pjjg) dzbdf,\n" +
                        "       f_pj_dzbdf_hxsq(hs, zbxdm, zbxz, pjjg)*zbxqz qzdf\n" +
                        "  from (select a.dydm,\n" +
                        "               b.scgs dymc,\n" +
                        "               a.zbxdm,\n" +
                        "               a.zbxz,\n" +
                        "               d.dydzcl,\n" +
                        "               b.ycyl cyl,\n" +
                        "               f_hs(b.nljcyl, b.nljcsl) hs,\n" +
                        "               f_pj_dzbpj_hxsq(f_hs(b.nljcyl, b.nljcsl), a.zbxdm, zbxz) pjjg,\n" +
                        "               c.zbxqz\n" +
                        "          from (\n" +
                        "                /*水驱储量动用程度*/\n" +
                        "                select a.dydm,\n" +
                        "                        '202012' ny,\n" +
                        "                        b.zbxdm,\n" +
                        "                        a.sqdycd zbxz,\n" +
                        "                        null zbxff,\n" +
                        "                        null pjjg\n" +
                        "                  from (select a.*\n" +
                        "                           from ss_dab09 a\n" +
                        "                          where  a.kffs = '后续水驱') a,\n" +
                        "                        (select a.zbxdm, a.nd from fl_zbqz_v a where xh = '1') b,\n" +
                        "                        (select a.dydm, a.nd, a.sqdycd from ss_dab09 a) c\n" +
                        "                 where a.nd = b.nd\n" +
                        "                   and a.dydm = c.dydm(+)\n" +
                        "                   and a.nd = c.nd(+)\n" +
                        "                   and a.nd = substr('202012', 0, 4)\n" +
                        "                union\n" +
                        "                /*压力系数*/\n" +
                        "                select a.dydm,\n" +
                        "                        '202012' ny,\n" +
                        "                        b.zbxdm,\n" +
                        "                        c.zbxz zbxz,\n" +
                        "                        null zbxff,\n" +
                        "                        null pjjg\n" +
                        "                  from (select a.*\n" +
                        "                           from ss_dab09 a\n" +
                        "                          where  a.kffs = '后续水驱') a,\n" +
                        "                        (select a.zbxdm, a.nd from fl_zbqz_v a where xh = '2') b,\n" +
                        "                        (select b.dydm,\n" +
                        "                                b.nd,\n" +
                        "                                b.mqdcyl,\n" +
                        "                                c.h,\n" +
                        "                                round(f_div(b.mqdcyl, b.ysdcyl), 3) zbxz\n" +
                        "                           from ss_dab09 b,\n" +
                        "                                (select b.dydm,\n" +
                        "                                        avg((a.skycds1 + a.skycds2) / 2) h\n" +
                        "                                   from ys_daa01 a, YD_DBA04 b\n" +
                        "                                  where a.skycds1 is not null\n" +
                        "                                    and a.skycds2 is not null\n" +
                        "                                    and a.jh = b.jh\n" +
                        "                                    and b.ny = '202012'\n" +
                        "                                    and b.DYDM IN\n" +
                        "                                        (select dydm\n" +
                        "                                           from ss_dab09 aa\n" +
                        "                                          where ND = substr('202012', 0, 4))\n" +
                        "                                  group by b.dydm) c\n" +
                        "                          where 1 = 1\n" +
                        "                            and b.dydm = c.dydm(+)) c\n" +
                        "                 where a.nd = b.nd\n" +
                        "                   and a.dydm = c.dydm(+)\n" +
                        "                   and a.nd = c.nd(+)\n" +
                        "                   and a.nd = substr('202012', 0, 4)\n" +
                        "                union\n" +
                        "                /*老井递减率*/\n" +
                        "                select a.dydm,\n" +
                        "                        '202012' ny,\n" +
                        "                        b.zbxdm,\n" +
                        "                        c.ljdjv zbxz,\n" +
                        "                        null zbxff,\n" +
                        "                        null pjjg\n" +
                        "                  from (select a.*\n" +
                        "                           from ss_dab09 a\n" +
                        "                          where  a.kffs = '后续水驱') a,\n" +
                        "                        (select a.zbxdm, a.nd from fl_zbqz_v a where xh = '3') b,\n" +
                        "                        (select a.dydm, F_YJ_NHDJL(v_rysp) ljdjv\n" +
                        "                           from (select a.dydm, a.v_rysp, xh\n" +
                        "                                   from (select a.dydm, a.v_rysp, rownum xh\n" +
                        "                                           from (select a.dydm,\n" +
                        "                                                        listagg(a.rysp, ',') within group(order by ny) v_rysp\n" +
                        "                                                   from (\n" +
                        "                                                         \n" +
                        "                                                         select a.dydm,\n" +
                        "                                                                 a.ny,\n" +
                        "                                                                 round(sum(hsycyl) /\n" +
                        "                                                                       get_rlts(ny)) rysp\n" +
                        "                                                           from yd_dba04 a\n" +
                        "                                                          where a.ny <= '202012'\n" +
                        "                                                            and a.ny >=\n" +
                        "                                                                (substr('202012',\n" +
                        "                                                                        0,\n" +
                        "                                                                        4) - 2) || 12\n" +
                        "                                                            and a.jh IN\n" +
                        "                                                                (SELECT JH\n" +
                        "                                                                   FROM YJ_QDDJ\n" +
                        "                                                                  where nd =\n" +
                        "                                                                        substr('202012',\n" +
                        "                                                                               0,\n" +
                        "                                                                               4) - 2)\n" +
                        "                                                            and substr(a.ny, 5, 6) != '00'\n" +
                        "                                                          group by a.dydm, a.ny\n" +
                        "                                                          order by a.dydm, a.ny) a\n" +
                        "                                                  where a.rysp != 0\n" +
                        "                                                    and a.rysp is not null\n" +
                        "                                                  group by a.dydm) a) a) a) c\n" +
                        "                 where a.nd = b.nd\n" +
                        "                   and a.nd = substr('202012', 0, 4)\n" +
                        "                   and a.dydm = c.dydm(+)\n" +
                        "                union\n" +
                        "                /*含水上升率*/\n" +
                        "                select a.dydm,\n" +
                        "                        '202012' ny,\n" +
                        "                        b.zbxdm,\n" +
                        "                        f_yj_hsssl(f_hs_jq(nvl(c.nLJCYL, 0), nvl(c.nljcsl, 0)),\n" +
                        "                                   f_hs_jq(nvl(e.nLJCYL, 0) - nvl(f.nljcyl, 0),\n" +
                        "                                           nvl(e.nljcsl, 0) - nvl(f.nljcsl, 0)),\n" +
                        "                                   c.cccd,\n" +
                        "                                   e.cccd,\n" +
                        "                                   c.cysd) zbxz,\n" +
                        "                        null zbxff,\n" +
                        "                        null pjjg\n" +
                        "                  from (select a.*\n" +
                        "                           from ss_dab09 a\n" +
                        "                          where  a.kffs = '后续水驱') a,\n" +
                        "                        (select a.zbxdm, a.nd from fl_zbqz_v a where xh = '4') b,\n" +
                        "                        yd_dbb01 c,\n" +
                        "                        yd_dbb01 e,\n" +
                        "                        yd_dbb01 f\n" +
                        "                 where a.nd = b.nd\n" +
                        "                   and a.dydm = c.dydm(+)\n" +
                        "                   and a.dydm = e.dydm(+)\n" +
                        "                   and a.dydm = f.dydm(+)\n" +
                        "                   and c.lbdm(+) = '3'\n" +
                        "                   and e.lbdm(+) = '3'\n" +
                        "                   and f.lbdm(+) = '3'\n" +
                        "                   and c.ny(+) = f_yj_hsssl_ny('202012', '1')\n" +
                        "                   and e.ny(+) = f_yj_hsssl_ny('202012', '3')\n" +
                        "                   and f.ny(+) = f_yj_hsssl_ny('202012', '4')\n" +
                        "                union\n" +
                        "                /*采收率*/\n" +
                        "                select a.dydm,\n" +
                        "                        '202012' ny,\n" +
                        "                        b.zbxdm,\n" +
                        "                        c.zbxz,\n" +
                        "                        null zbxff,\n" +
                        "                        null pjjg\n" +
                        "                  from (select a.*\n" +
                        "                           from ss_dab09 a\n" +
                        "                          where  a.kffs = '后续水驱') a,\n" +
                        "                        (select a.zbxdm, a.nd from fl_zbqz_v a where xh = '5') b,\n" +
                        "                        (select a.dydm, a.nd, a.csl zbxz from ss_dab09 a) c\n" +
                        "                 where a.nd = b.nd\n" +
                        "                   and a.nd = substr('202012', 0, 4)\n" +
                        "                   and a.dydm = c.dydm(+)\n" +
                        "                   and a.nd = c.nd(+)\n" +
                        "                union\n" +
                        "                /*吨储盈利*/\n" +
                        "                select a.dydm,\n" +
                        "                        '202012' ny,\n" +
                        "                        b.zbxdm,\n" +
                        "                        c.zbxz zbxz,\n" +
                        "                        null zbxff,\n" +
                        "                        null pjjg\n" +
                        "                  from (select a.*\n" +
                        "                           from ss_dab09 a\n" +
                        "                          where  a.kffs = '后续水驱') a,\n" +
                        "                        (select a.zbxdm, a.nd from fl_zbqz_v a where xh = '6') b,\n" +
                        "                        (select a.dydm, a.nd, a.dyczcb zbxz from ss_dab09 a) c\n" +
                        "                 where a.nd = b.nd\n" +
                        "                   and a.nd = substr('202012', 0, 4)\n" +
                        "                   and a.dydm = c.dydm(+)\n" +
                        "                   and a.nd = c.nd(+)) a,\n" +
                        "               YD_dbb01 b,\n" +
                        "               ss_sqqz c,\n" +
                        "               ss_dab09 d\n" +
                        "           where a.dydm = b.dydm\n" +
                        "           and a.zbxdm=c.zbxdm\n" +
                        "           and a.dydm=d.dydm\n" +
                        "           and c.yclxdm='hxsq'\n" +
                        "           and b.ny = '202012'\n" +
                        "           and b.lbdm = 3) a\n" +
                        "          /*排除异常*/\n" +
                        "           where a.hs!=100\n" +
                        "           and   a.cyl>10\n" +
                        "           and   a.dydzcl>50\n" +
                        "           and   a.dymc not like '%其它%'\n" +
                        "                           \n" +
                        "                           \n" +
                        "                           \n" +
                        "                           \n" +
                        "                           \n" +
                        "                           \n" +
                        "                           ) a\n" +
                        "                \n" +
                        "                pivot(max(zbxz) zbxz, max(pjjg) pjjg, max(dzbdf) dzbdf, max(qzdf) qzdf\n" +
                        "                   for zbxdm in('sqcldycd' as sqcldycd,\n" +
                        "                               'ylxs' as ylxs,\n" +
                        "                               'ljdjl' as ljdjl,\n" +
                        "                               'hsssl' as hsssl,\n" +
                        "                               'csl'   as csl,\n" +
                        "                               'dyczcb' as dyczcb))) a) a,\n" +
                        "                             ss_dab09 b                             \n" +
                        " where a.dydm=b.dydm\n" +
                        " ) a\n" +
                        " ) a\n"));
    }



    private static final Set<String> BEGIN_CLAUSES = new HashSet<String>();
    private static final Set<String> END_CLAUSES = new HashSet<String>();
    private static final Set<String> LOGICAL = new HashSet<String>();
    private static final Set<String> QUANTIFIERS = new HashSet<String>();
    private static final Set<String> DML = new HashSet<String>();
    private static final Set<String> MISC = new HashSet<String>();
    static final String indentString = "    ";
    static final String initial = "\n    ";

    public String format(String source) {
        return new FormatProcess(source).perform().trim();
    }

    static {
        BEGIN_CLAUSES.add("left");
        BEGIN_CLAUSES.add("right");
        BEGIN_CLAUSES.add("inner");
        BEGIN_CLAUSES.add("outer");
        BEGIN_CLAUSES.add("group");
        BEGIN_CLAUSES.add("order");

        END_CLAUSES.add("where");
        END_CLAUSES.add("set");
        END_CLAUSES.add("having");
        END_CLAUSES.add("join");
        END_CLAUSES.add("from");
        END_CLAUSES.add("by");
        END_CLAUSES.add("join");
        END_CLAUSES.add("into");
        END_CLAUSES.add("union");

        LOGICAL.add("and");
        LOGICAL.add("or");
        LOGICAL.add("when");
        LOGICAL.add("else");
        LOGICAL.add("end");

        QUANTIFIERS.add("in");
        QUANTIFIERS.add("all");
        QUANTIFIERS.add("exists");
        QUANTIFIERS.add("some");
        QUANTIFIERS.add("any");

        DML.add("insert");
        DML.add("update");
        DML.add("delete");

        MISC.add("select");
        MISC.add("on");
    }

    private static class FormatProcess {
        boolean beginLine = true;
        boolean afterBeginBeforeEnd = false;
        boolean afterByOrSetOrFromOrSelect = false;
        boolean afterValues = false;
        boolean afterOn = false;
        boolean afterBetween = false;
        boolean afterInsert = false;
        int inFunction = 0;
        int parensSinceSelect = 0;
        private LinkedList<Integer> parenCounts = new LinkedList<Integer>();
        private LinkedList<Boolean> afterByOrFromOrSelects = new LinkedList<Boolean>();

        int indent = 1;

        StringBuffer result = new StringBuffer();
        StringTokenizer tokens;
        String lastToken;
        String token;
        String lcToken;

        public FormatProcess(String sql) {
            this.tokens = new StringTokenizer(sql, "()+*/-=<>'`\"[], \n\r\f\t", true);
        }

        public String perform() {
            this.result.append("\n    ");

            while (this.tokens.hasMoreTokens()) {
                this.token = this.tokens.nextToken();
                this.lcToken = this.token.toLowerCase();

                if ("'".equals(this.token)) {
                    String t;
                    do {
                        t = this.tokens.nextToken();
                        this.token += t;
                    } while ((!"'".equals(t)) && (this.tokens.hasMoreTokens()));
                } else if ("\"".equals(this.token)) {
                    String t;
                    do {
                        t = this.tokens.nextToken();
                        this.token += t;
                    } while (!"\"".equals(t));
                }

                if ((this.afterByOrSetOrFromOrSelect) && (",".equals(this.token))) {
                    commaAfterByOrFromOrSelect();
                } else if ((this.afterOn) && (",".equals(this.token))) {
                    commaAfterOn();
                } else if ("(".equals(this.token)) {
                    openParen();
                } else if (")".equals(this.token)) {
                    closeParen();
                } else if (BasicFormatterImpl.BEGIN_CLAUSES.contains(this.lcToken)) {
                    beginNewClause();
                } else if (BasicFormatterImpl.END_CLAUSES.contains(this.lcToken)) {
                    endNewClause();
                } else if ("select".equals(this.lcToken)) {
                    select();
                } else if (BasicFormatterImpl.DML.contains(this.lcToken)) {
                    updateOrInsertOrDelete();
                } else if ("values".equals(this.lcToken)) {
                    values();
                } else if ("on".equals(this.lcToken)) {
                    on();
                } else if ((this.afterBetween) && (this.lcToken.equals("and"))) {
                    misc();
                    this.afterBetween = false;
                } else if (BasicFormatterImpl.LOGICAL.contains(this.lcToken)) {
                    logical();
                } else if (isWhitespace(this.token)) {
                    white();
                } else {
                    misc();
                }

                if (!isWhitespace(this.token)) {
                    this.lastToken = this.lcToken;
                }
            }

            return this.result.toString();
        }

        private void commaAfterOn() {
            out();
            this.indent -= 1;
            newline();
            this.afterOn = false;
            this.afterByOrSetOrFromOrSelect = true;
        }

        private void commaAfterByOrFromOrSelect() {
            out();
            newline();
        }

        private void logical() {
            if ("end".equals(this.lcToken)) {
                this.indent -= 1;
            }
            newline();
            out();
            this.beginLine = false;
        }

        private void on() {
            this.indent += 1;
            this.afterOn = true;
            newline();
            out();
            this.beginLine = false;
        }

        private void misc() {
            out();
            if ("between".equals(this.lcToken)) {
                this.afterBetween = true;
            }
            if (this.afterInsert) {
                newline();
                this.afterInsert = false;
            } else {
                this.beginLine = false;
                if ("case".equals(this.lcToken))
                    this.indent += 1;
            }
        }

        private void white() {
            if (!this.beginLine)
                this.result.append(" ");
        }

        private void updateOrInsertOrDelete() {
            out();
            this.indent += 1;
            this.beginLine = false;
            if ("update".equals(this.lcToken)) {
                newline();
            }
            if ("insert".equals(this.lcToken))
                this.afterInsert = true;
        }

        private void select() {
            out();
            this.indent += 1;
            newline();
            this.parenCounts.addLast(new Integer(this.parensSinceSelect));
            this.afterByOrFromOrSelects.addLast(Boolean.valueOf(this.afterByOrSetOrFromOrSelect));
            this.parensSinceSelect = 0;
            this.afterByOrSetOrFromOrSelect = true;
        }

        private void out() {
            this.result.append(this.token);
        }

        private void endNewClause() {
            if (!this.afterBeginBeforeEnd) {
                this.indent -= 1;
                if (this.afterOn) {
                    this.indent -= 1;
                    this.afterOn = false;
                }
                newline();
            }
            out();
            if (!"union".equals(this.lcToken)) {
                this.indent += 1;
            }
            newline();
            this.afterBeginBeforeEnd = false;
            this.afterByOrSetOrFromOrSelect = (("by".equals(this.lcToken)) || ("set".equals(this.lcToken))
                    || ("from".equals(this.lcToken)));
        }

        private void beginNewClause() {
            if (!this.afterBeginBeforeEnd) {
                if (this.afterOn) {
                    this.indent -= 1;
                    this.afterOn = false;
                }
                this.indent -= 1;
                newline();
            }
            out();
            this.beginLine = false;
            this.afterBeginBeforeEnd = true;
        }

        private void values() {
            this.indent -= 1;
            newline();
            out();
            this.indent += 1;
            newline();
            this.afterValues = true;
        }

        private void closeParen() {
            this.parensSinceSelect -= 1;
            if (this.parensSinceSelect < 0) {
                this.indent -= 1;
                this.parensSinceSelect = ((Integer) this.parenCounts.removeLast()).intValue();
                this.afterByOrSetOrFromOrSelect = ((Boolean) this.afterByOrFromOrSelects.removeLast()).booleanValue();
            }
            if (this.inFunction > 0) {
                this.inFunction -= 1;
                out();
            } else {
                if (!this.afterByOrSetOrFromOrSelect) {
                    this.indent -= 1;
                    newline();
                }
                out();
            }
            this.beginLine = false;
        }

        private void openParen() {
            if ((isFunctionName(this.lastToken)) || (this.inFunction > 0)) {
                this.inFunction += 1;
            }
            this.beginLine = false;
            if (this.inFunction > 0) {
                out();
            } else {
                out();
                if (!this.afterByOrSetOrFromOrSelect) {
                    this.indent += 1;
                    newline();
                    this.beginLine = true;
                }
            }
            this.parensSinceSelect += 1;
        }

        private static boolean isFunctionName(String tok) {
            char begin = tok.charAt(0);
            boolean isIdentifier = (Character.isJavaIdentifierStart(begin)) || ('"' == begin);
            return (isIdentifier) && (!BasicFormatterImpl.LOGICAL.contains(tok))
                    && (!BasicFormatterImpl.END_CLAUSES.contains(tok))
                    && (!BasicFormatterImpl.QUANTIFIERS.contains(tok)) && (!BasicFormatterImpl.DML.contains(tok))
                    && (!BasicFormatterImpl.MISC.contains(tok));
        }

        private static boolean isWhitespace(String token) {
            return " \n\r\f\t".indexOf(token) >= 0;
        }

        private void newline() {
            this.result.append("\n");
            for (int i = 0; i < this.indent; i++) {
                this.result.append("    ");
            }
            this.beginLine = true;
        }
    }


}




