/*
 * $Id: om-grid.js,v 1.97 2012/01/04 03:28:04 zhoufazhi Exp $
 * operamasks-ui omGrid @VERSION
 *
 * Copyright 2011, AUTHORS.txt (http://ui.operamasks.org/about)
 * Dual licensed under the MIT or GPL Version 2 licenses.
 * http://ui.operamasks.org/license
 *
 * http://ui.operamasks.org/docs/
 * 
 * Depends:
 *  jquery.ui.widget.js
 *  jquery.ui.core.js
 *  jquery.ui.mouse.js
 *  jquery.ui.resizable.js
 *  om-grid.js
 */ 
/**
     * @name omGridExtend
     * @author 丁超
     * @class 表格组件。扩展自om.omGrid，增加了排序和显示详情功能<br/><br/>
     * <b>示例：</b><br/>
     * <pre>
     * &lt;script type="text/javascript" >
     *   $('#grid').omGridExtend({
	 *			//是否显示详情
	 *			showDetail:true,
	 *			singleSelect:false,
     *          dataSource : 'griddata.do?method=fast',
     *           colModel : [ {header : 'ID', name : 'id', width : 100, align : 'center'}, 
     *                        {header : '地区', name : 'city', width : 120, align : 'center'}, 
     *                        {header : '地址', name : 'address', align : 'center', width : 'autoExpand'} ],
     *				onRowClick:function(index,row){
	 *				
	 *			},
	 *			onRowSelect:function(){
	 *				
	 *			},
	 *			onRowDeselect:function(){
	 *				
	 *			},
	 *			//当详情展开的时候调用的回调
	 *			onDetailExpand:function(rowData,row){
	 *				
	 *			},
	 *			//初始化的时候以哪一个字段排序
	 *			sortName:'address',
	 *			//排序方式
	 *			sortOrder:'desc',
	 *			method:'get',
	 *			onRowCheck:function(index){
	 *				
	 *			}
     *      });
     * &lt;/script>
     * 
     * &lt;table id="mytable"/>
     * </pre>
     * 
     */
;(function($) {
    $.widget('om.omGridExtend',$.om.omGrid, {
        options:/** @lends omGridExtend#*/{
			/**
			  *是否显示详情
			  *@default false
			  *@type Boolean
			  *@example
			  *$('.selector').omGrid({showDetail : false});
			  */
			showDetail:false,
			/**
			  *详情展开的时候是否重新加载
			  *@default true
			  *@type Boolean
			  *@example
			  *$('.selector').omGrid({detailExpandReload : false});
			  */
			detailExpandReload:true,
            /**
			  *详情展开时候的回调函数
			  *@default function(rowData,detailContainer){}
			  *@type Function
			  *@example
			  *$('.selector').omGrid({onDetailExpand : function(rowData,detailContainer){}});
			  */
			onDetailExpand : function(rowData,detailContainer){},
			/**
			  *初始化的时候以哪个字段作为排序字段
			  *@default null
			  *@type String
			  *@example
			  *$('.selector').omGrid({sortName : 'address'});
			  */
			sortName:null,
			/**
			  *排序方式  enmu{"desc","asc"}
			  *@default asc
			  *@type String
			  *@example
			  *$('.selector').omGrid({sortName : 'address'});
			  */
			sortOrder:'asc'
        },
        //private methods
        _create:function(){
			//call super method
			$.om.omGrid.prototype._create.call(this);

            this._bindHeadClickEnvent();
			this._bindDetailEvent();
        },
        _buildTableHead:function(){
            var op=this.options,
                el=this.element,
                grid = el.closest('.om-grid'),
                cms=op.colModel,
                allColsWidth = 0, //colModel的宽度
                indexWidth = 0, //colModel的宽度
                checkboxWidth = 0, //colModel的宽度
                autoExpandColIndex = -1;
                thead=$('<thead></thead');
                tr=$('<tr></tr>').appendTo(thead);
				this.detailColSpan = 0;
			//渲染显示明细列
			if(op.showDetail){
				var cell = $('<th></th>').attr({axis:'detailCol',align:'center'}).addClass('unsort detailCol').append($('<div class="indexheader" style="text-align:center;width:25px;"></div'));
				tr.append(cell);
                indexWidth=25;
				this.detailColSpan++;
			}
            //渲染序号列
            if(op.showIndex){
                var cell=$('<th></th').attr({axis:'indexCol',align:'center'}).addClass('unsort indexCol').append($('<div class="indexheader" style="text-align:center;width:25px;"></div'));
                tr.append(cell);
                indexWidth=25;
				this.detailColSpan++;
            }
            //渲染checkbox列
            if(!op.singleSelect){
                var cell=$('<th></th').attr({axis:'checkboxCol',align:'center'}).addClass('unsort checkboxCol').append($('<div class="checkboxheader" style="text-align:center;width:17px;"><span class="checkbox"/></div'));
                tr.append(cell);
                checkboxWidth=17;
				this.detailColSpan++;
            }
            //渲染colModel各列
            for (var i=0,len=cms.length;i<len;i++) {
                var cm=cms[i],cmWidth = cm.width || 60,cmAlign=cm.align || 'center';
                if(cmWidth == 'autoExpand'){
                    cmWidth = 0;
                    autoExpandColIndex = i;
                }
                var thCell=$('<div></div').html(cm.header).css({'text-align':cmAlign,width:cmWidth});
				if(this.options.sortName && this.options.sortName === cm.name){
					if(this.options.sortOrder === 'asc'){
						thCell.addClass('sasc');
					}else{
						thCell.addClass('sdesc');
					}
				}
                cm.wrap && thCell.addClass('wrap');
                var th=$('<th></th').attr('axis', 'col' + i).addClass('col' + i).append(thCell);
                if(cm.name) {
                    th.attr('abbr', cm.name);
                }
                if(cm.align) {
                    th.attr('align',cm.align);
                }
                //var _div=$('<div></div>').html(cm.header).attr('width', cmWidth);
                allColsWidth += cmWidth;
                tr.append(th);
				this.detailColSpan++;
            }
            //tr.append($('<th></th'));
            el.prepend(thead);
            
            var hDiv = $('<div class="hDiv om-state-default"></div>').append('<div class="hDivBox"><table cellPadding="0" cellSpacing="0"></table></div>');
            el.parent().before(hDiv);
            
                $('table',hDiv).append(thead);
            //修正各列的列宽
            if(autoExpandColIndex != -1){ //说明有某列要自动扩充
                var tableWidth=grid.width()-20,
                    //usableWidth=tableWidth-allColsWidth-indexWidth-checkboxWidth;
                    usableWidth=tableWidth-thead.width();
                    toBeExpandedTh=tr.find('th[axis="col'+autoExpandColIndex+'"] div');
                if(usableWidth<=0){
                    toBeExpandedTh.css('width',60);
                }else{
                    toBeExpandedTh.css('width',usableWidth);
                }
            }else if(op.autoFit){
                //var tableWidth=el.width(),
                 //   usableWidth=tableWidth-indexWidth-checkboxWidth;
                var tableWidth=grid.width()-20,
                    usableWidth=tableWidth-thead.width(),
                    percent=1+usableWidth/allColsWidth,
                    toFixedThs=tr.find('th[axis^="col"] div');
                for (var i=0,len=cms.length;i<len;i++) {
                    var col=toFixedThs.eq(i);
                    col.css('width',parseInt(col.width()*percent));
                }
            }
            this.thead=thead;
            thead = null;
        },
		_bindHeadClickEnvent:function(){
			var self = this,
				op = this.options;
			this.thead.delegate('th:not(.unsort)','click',function(event){
				var abbr = $(this).attr('abbr');
				op.sortName = abbr;
				$('th>div',self.thead).removeClass('sasc').removeClass('sdesc');
				op.sortOrder === 'asc' ? (op.sortOrder = 'desc',$('>div',this).addClass('sdesc')):(op.sortOrder = 'asc',$('>div',this).addClass('sasc'));
				self._populate();
			});
			 
		},
		_renderDatas:function(from,to){
            var self=this,
                el=this.element,
                op=this.options,
                grid=el.closest('.om-grid'),
                gridHeaderCols=$('.hDiv thead tr:first th',grid),
                rows=this.pageData.data.rows,
                colModel=op.colModel,
                rowClasses=op.rowClasses,
                tbody=$('tbody',el).empty(),
                isRowClassesFn= (typeof rowClasses === 'function'),
                pageData = this.pageData,start=(pageData.nowPage-1)*op.limit;
            $.each(rows,function(i, rowData) {
                var rowCls = isRowClassesFn? rowClasses(i,rowData):rowClasses[i % rowClasses.length];
                var tr=$('<tr></tr>').addClass('om-grid-row').addClass(rowCls).attr('rowindex',i);
                var rowValues=self._buildRowCellValues(colModel,rowData,i);
                $(gridHeaderCols).each(function(){
                    var axis = $(this).attr('axis'),wrap=false,html;
					if(axis == 'detailCol'){
						 html = '<span class="detailIcon collapsed"/>';
					}
                    else if(axis == 'indexCol'){
                        html=i+start+1;
                    }else if(axis == 'checkboxCol'){
                        html = '<span class="checkbox"/>';
                    }else{
                        var colIndex=axis.substring(3);
                        html=rowValues[colIndex];
                        if(colModel[colIndex].wrap){
							wrap=true;
						} 
                    }
                    var td = $('<td></td>').attr({align:this.align,abbr:this.abbr}).addClass(axis).append($('<div></div>').html(html).addClass(wrap?'wrap':'').attr({'align':this.align}).width($('div',$(this)).width()));
                    tr.append(td);
                });
                tbody.append(tr);
            });
        },
        //刷新数据
        _populate : function() { // get latest data
            var self=this,
                el = this.element,
                grid = el.closest('.om-grid'),
                op = this.options,
                pageStat = $('.pPageStat', grid);
            if (!op.dataSource) {
                $('.pPageStat', grid).html(op.emptygMsg);
                return false;
            }
            if (this.loading) {
                return true;
            }
            var pageData = this.pageData,
                nowPage = pageData.nowPage || 1,
                loadMask = $('.gBlock',grid);
            //具备加载数据的前提条件了，准备加载
            this.loading = true;
            pageStat.html(op.loadingMsg);
            loadMask.show();
            if ($.browser.opera) {
                $(grid).css('visibility', 'hidden');
            }
            var limit = (op.limit<=0)?100000000:op.limit;
            var param = [ {
                name : 'start',
                value : limit * (nowPage - 1)
            }, {
                name : 'limit',
                value : limit
            }, {
                name : '_time_stamp_',
                value : new Date().getTime()
            } ];
			//push sort options
			if(op.sortName){
				param.push({
					name : 'sortName',
					value : op.sortName 
				});
				param.push({
					name : 'sortOrder',
					value : op.sortOrder
				});
			}
            $.ajax({
                type : op.method,
                url : op.dataSource,
                data : param,
                dataType : 'json',
                success : function(data,textStatus,request) {
                    var onSuccess = op.onSuccess;
                    if (typeof(onSuccess) == 'function') {
                        onSuccess(data,textStatus,request);
                    }
                    self._addData(data);
                    op.onRefresh(nowPage,data.rows);
                    loadMask.hide();
                    self.loading = false;
                },
                error : function(XMLHttpRequest, textStatus, errorThrown) {
                    pageStat.html(op.errorMsg).css('color','red');
                    try {
                        var onError = op.onError;
                        if (typeof(onError) == 'function') {
                            onError(XMLHttpRequest, textStatus, errorThrown);
                        }
                    } catch (e) {
                        // do nothing 
                    } finally {
                        loadMask.hide();
                        self.loading = false;
                        return false;
                    }
                    
                }
            });
        },
        //绑定行选择/行反选/行单击/行双击等事件监听
        _bindSelectAndClickEnvent:function(){
            var self=this;
            //如果有checkbo列则绑定事件
            if(!this.options.singleSelect){ //可以多选
                // 全选/反选,不需要刷新headerChekcbox的选择状态
                $('th.checkboxCol span.checkbox',this.thead).click(function(){
                    var thCheckbox=$(this),trSize=$('tr.om-grid-row',this.tbody).size();
                    if(thCheckbox.hasClass('selected')){ //说明是要全部取消选择
                        thCheckbox.removeClass('selected');
                        for(var i=0;i<trSize;i++){
                            self._rowDeCheck(i);
                        }
                    }else{ //说明是要全选
                        thCheckbox.addClass('selected');
                        for(var i=0;i<trSize;i++){
                            self._rowCheck(i);
                        }
                    }
                });
                //行单击,需要刷新headerChekcbox的选择状态
                this.tbody.delegate('tr.om-grid-row  span.checkbox','click',function(){
                    var row=$(this),index=row.closest('tr',self.tbody).index();
                    if(row.hasClass('selected')){ //已选择
                        self._rowDeCheck(index);
                    }else{
                        self._rowCheck(index);
                    }
                    self._refreshHeaderCheckBox();
                    //self.options.onRowClick(index,self._getRowData(index));
                });
                //行双击
                /*this.tbody.delegate('tr.om-grid-row','dblclick',function(){
                    var row=$(this),index=row.index();
                    if(row.hasClass('om-state-highlight')){ //已选择
                        //do nothing
                    }else{
                        self._rowSelect(index);
                        self._refreshHeaderCheckBox();
                    }
                    self.options.onRowDblClick(index,self._getRowData(index));
                });*/
            }//else{ //不可多选
                //行单击
                this.tbody.delegate('tr.om-grid-row','click',function(event){
					if($(event.target).hasClass('checkbox')||$(event.target).hasClass('detailIcon'))
						return false;
                    var row=$(this),index=row.index();
                    if(row.hasClass('om-state-highlight')){ //已选择
                        // no need to deselect another row and select this row
                    }else{
                        var lastSelectedIndex = $('tr.om-state-highlight',self.tbody).index();
                        (lastSelectedIndex != -1) && self._rowDeSelect(lastSelectedIndex);
                        self._rowSelect(index);
                    }
                    self.options.onRowClick(index,self._getRowData(index));
                });
                
                //行双击,因为双击一定会先触发单击，所以对于单选表格双击时这一行一定是选中的，所以不需要强制双击前选中
                this.tbody.delegate('tr.om-grid-row','dblclick',function(){
                    var index=$(this).index();
                    self.options.onRowDblClick(index,self._getRowData(index));
                });
          //  }
        },
		_bindDetailEvent:function(){
			var self = this;
			this.tbody.delegate('span.detailIcon','click',function(){
				if($(this).hasClass('collapsed')){
					$(this).removeClass('collapsed');
					$(this).addClass('expanded');
					var index = parseInt($(this).parents('tr.om-grid-row').attr('rowindex'));
					self._expand($(this).parents('tr.om-grid-row'),index);
				}else{
					$(this).removeClass('expanded');
					$(this).addClass('collapsed');
					var index = parseInt($(this).parents('tr.om-grid-row').attr('rowindex'));
					self._collapse($(this).parents('tr.om-grid-row'),index);
				}
			});
		},
		//展开
		_expand:function(row,index){
			var rowData = this._getRowData(index);
			var detail = $(row).data('detail');
			if(this.options.detailExpandReload){
				detail = null;
			}
			if(!detail){
				detail = $('<tr class="om-grid-row-detail"><td colspan="'+this.detailColSpan+'"><div class="om-grid-row-detail-wrapper">detail</div></td></tr>').insertAfter($(row)).attr({rowindex:index});
				$(row).data('detail',detail);
				this.options.onDetailExpand(rowData,$('div.om-grid-row-detail-wrapper',detail));
			}else{
				detail.show();
			}
			
		},
		//关闭
		_collapse:function(row,index){
			var rowData = this._getRowData(index);
			var detail = $(row).data('detail');
			if(!detail)
				return;
			if(this.options.detailExpandReload){
				detail.remove();
			}else{
				detail.hide();
			}
		},
        _rowSelect:function(index){
             var el=this.element,
                op=this.options,
                tbody=$('tbody',el),
                tr=$('tr:eq('+index+')',tbody),
                chk=$('td.checkboxCol span.checkbox',tr);
            tr.addClass('om-state-highlight');
            //chk.addClass('selected');
            op.onRowSelect(index,this._getRowData(index));
        },
        _rowDeSelect:function(index){
            var self=this,
                el=this.element,
                op=this.options,
                tbody=$('tbody',el),
                tr=$('tr:eq('+index+')',tbody),
                chk=$('td.checkboxCol span.checkbox',tr);
            tr.removeClass('om-state-highlight');
            //chk.removeClass('selected');
            op.onRowDeselect(index,this._getRowData(index));
        },
		_rowCheck:function(index){
             var el=this.element,
                op=this.options,
                tbody=$('tbody',el),
                tr=$('tr:eq('+index+')',tbody),
                chk=$('td.checkboxCol span.checkbox',tr);
            chk.addClass('selected');
			op.onRowCheck(index,this._getRowData(index));
        },
		_rowDeCheck:function(index){
            var self=this,
                el=this.element,
                op=this.options,
                tbody=$('tbody',el),
                tr=$('tr:eq('+index+')',tbody),
                chk=$('td.checkboxCol span.checkbox',tr);
            chk.removeClass('selected');
        },
        //让列可以改变宽度（index列和checkbox列不可以改变宽度）
        /**
         * 选择行。<b>注意：传入的参数是序号（第一行是0第二行是1）数组（比如[0,1]表示选择第一行和第二行）；要想清除所有选择，请使用空数组[]作为参数；只能传入序号数组，如果要做复杂的选择算法，请先在其它地方算好序号数组后后调用此方法；此方法会清除其它选择状态，如选择第1,2行然后setSelections([3])最后结果中只有第3行，如setSelections([3,4]);setSelections([5,6])后只会选择5,6两行</b>。
         * @name omGrid#setSelection
         * @function
         * @param indexes 序号（第一行是0第二行是1）数组。
         * @returns jQuery对象
         * @example
         * //选择表格中第二行、第三行、第五行
         * $('.selector').omGridExtend('setSelection',2);
         * 
         */
        setSelection : function(index){
            var self=this;
            /*if(!$.isArray(indexes)){
                indexes=[indexes];
            }*/
            var selected=this.getSelection();
           /* $(selected).each(function(){
                self._rowDeSelect(this);
            });
            $(indexes).each(function(){
                self._rowSelect(this);
            });*/
			if( selected )
				this._rowDeSelect(selected);
			this._rowSelect( index );
        },
        /**
         * 获取选择的行的行号或行记录。<b>注意：默认返回的是行序号组成的数组（如选择了第2行和第5行则返回[1,4]），如果要返回行记录JSON组成的数组需要传入一个true作为参数</b>。
         * @name omGrid#getSelection
         * @function
         * @param needRecords 参数为true时返回的不是行序号数组而是行记录数组。参数为空或不是true时返回行序号数组。
         * @returns jQuery对象
         * @example
         * var selectedIndexed = $('.selector').omGridExtend('getSelection');
         * var selectedRecords = $('.selector').omGridExtend('getSelection',true);
         * 
         */
        getSelection:function(needRecord){
            //needRecords=true时返回Record[],不设或为false时返回index[]
            var self=this,tr=$('tr.om-state-highlight',this.tbody),result;
            if(needRecord){
                var rows=self.pageData.data.rows;
                if(tr.length == 0){
					result = null;
				}else{
					result = rows[tr.attr('rowindex')];
				}
            }else{
                if(tr.length == 0){
					result = null;
				}else{
					result = tr.attr('rowindex');
				}
            }
            return result;
        },
		 /**
         * 获取选择的行的行号或行记录。<b>注意：默认返回的是行序号组成的数组（如选择了第2行和第5行则返回[1,4]），如果要返回行记录JSON组成的数组需要传入一个true作为参数</b>。
         * @name omGrid#getChecks
         * @function
         * @param needRecords 参数为true时返回的不是行序号数组而是行记录数组。参数为空或不是true时返回行序号数组。
         * @returns jQuery对象
         * @example
         * var selectedIndexed = $('.selector').omGridExtend('getChecks');
         * var selectedRecords = $('.selector').omGridExtend('getChecks',true);
         * 
         */
		getChecks : function(needRecords){
			var self = this , checks = $('span.selected',this.tbody),result = [];
			if(needRecords){
				var rows = this.pageData.data.rows;
				checks.each(function(i){
					var tr = $(this).parents('tr.om-grid-row');
					result[i] = rows[tr.attr('rowindex')];
				});
			}else{
				checks.each(function(i){
					var tr = $(this).parents('tr.om-grid-row');
					result[i] = tr.attr('rowindex');
				});
			}

			return result;
		},
		/**
         * 选择行。<b>注意：传入的参数是序号（第一行是0第二行是1）数组（比如[0,1]表示选择第一行和第二行）；要想清除所有选择，请使用空数组[]作为参数；只能传入序号数组，如果要做复杂的选择算法，请先在其它地方算好序号数组后后调用此方法；此方法会清除其它选择状态，如选择第1,2行然后setSelections([3])最后结果中只有第3行，如setSelections([3,4]);setSelections([5,6])后只会选择5,6两行</b>。
         * @name omGrid#setChecks
         * @function
         * @param indexes 序号（第一行是0第二行是1）数组。
         * @returns jQuery对象
         * @example
         * //选择表格中第二行、第三行、第五行
         * $('.selector').omGridExtend('setChecks',[1,2,4]);
         * 
         */
		setChecks : function(indexes){
			var self = this ; 
			if(!$.isArray(indexes)){
                indexes=[indexes];
            }

			$(indexes).each(function(){
				self._rowCheck(this);
			});

		}
    });
})(jQuery);